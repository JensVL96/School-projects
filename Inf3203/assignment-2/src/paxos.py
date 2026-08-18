import time
import requests
import logging

logging.basicConfig(
    level=logging.DEBUG,  # Or logging.INFO for less verbose output
    format='%(asctime)s - %(levelname)s - %(message)s'
)

# paxos.py
class ProposalID:
    """Unique identifier for Paxos proposals with comparison operations."""
    def __init__(self, number, uid):
        self.number = number
        self.uid = uid

    def __lt__(self, other):
        """Compare proposal IDs for ordering (number then uid)."""
        if other is None:
            return False
        return (self.number < other.number) or (
            self.number == other.number and self.uid < other.uid
        )

    def __ge__(self, other):
        """Inverse of __lt__ for completeness."""
        return not self.__lt__(other)

    def __str__(self):
        """String representation for logging/debugging."""
        return f"{self.number}-{self.uid}"


"""Implements the Proposer role in Paxos consensus.
    Args:
        messenger: Communication handler
        proposer_uid: Unique node identifier
        quorum_size: Minimum responses needed for consensus
"""
class Proposer:
    def __init__(self, messenger: any, proposer_uid: str, quorum_size: str):
        # Configuration
        self.messenger = messenger
        self.proposer_uid = proposer_uid
        self.quorum_size = quorum_size

        # State variables
        self.proposal_id = ProposalID(0, proposer_uid)
        self.proposed_value = None
        self.promises_rcvd = set()
        self.sequence_number = 0
        self.proposal_in_progress = False
    
    def set_proposal(self, value: str):
        """Initiate a new proposal with the given value."""
        self.proposed_value = value
        self.proposal_in_progress = True
        self.prepare()

    def prepare(self):
        """Phase 1: Broadcast PREPARE messages to acceptors."""
        node_num = int(self.proposer_uid[4:])
        # Generates unique sequence number combining:
            # - Timestamp (high bits)
            # - Node ID (middle bits) 
            # - Sequence counter (low bits)
        # self.sequence_number = int(time.time_ns()/1000) << 16 | (node_num << 8) | (self.sequence_number % 256)
        self.sequence_number = int(time.time() * 1000) * 100 + node_num
        self.proposal_id = ProposalID(self.sequence_number, self.proposer_uid)
        self.promises_rcvd = set()

        self._trace('prepare_sent', from_node=self.proposer_uid)
        self.messenger.send_prepare(self.proposal_id, self.recv_promise)

    """
    phase 1:
    Handle PROMISE responses from acceptors.
    
    Args:
        from_uid: Responding node ID
        proposal_id: The proposal ID being promised
        prev_accepted_id: Highest accepted ID from acceptor (if any)
        prev_accepted_value: Corresponding value from acceptor (if any)
    """
    def recv_promise(self, from_uid, proposal_id, prev_accepted_id, prev_accepted_value):
        if proposal_id != self.proposal_id:
            return

        self.promises_rcvd.add(from_uid)

        # Update with any previously accepted value
        if prev_accepted_id and prev_accepted_id > self.proposal_id:
            self.proposed_value = prev_accepted_value

        # Count the number of promises received
        total_promises = len(self.promises_rcvd)
        if self.proposer_uid not in self.promises_rcvd:
            total_promises += 1  # Count self

        self._trace('promise_received', 
                    from_node=from_uid,
                    prev_accepted_id=str(prev_accepted_id) if prev_accepted_id else None,
                    promises_received=total_promises)

        if total_promises >= self.quorum_size:
            self.messenger.send_accept_request(self.proposal_id, self.proposed_value)

    def _trace(self, event: str, **kwargs: any):
        """Helper for debug tracing."""
        self.messenger.debugger.trace_proposal(
            str(self.proposal_id),
            self.proposed_value,
            event,
            **kwargs
        )

"""Implements the Acceptor role in Paxos consensus.
    Arg:
        messenger: Communication handler
"""
class Acceptor:
    def __init__(self, messenger):
        # Configuration
        self.messenger = messenger

        # Protocol state
        self.promised_id = None
        self.accepted_id = None
        self.accepted_value = None

    """
    phase 1:
    Handle PREPARE messages from proposers.

    Args:
        from_uid: Proposer's node ID
        proposal_id: Proposal ID being proposed

    Returns:
        Tuple of (accepted, prev_accepted_id, prev_accepted_value)
    """
    def recv_prepare(self, from_uid, proposal_id):
        if self.promised_id is None or proposal_id > self.promised_id:
            self.promised_id = proposal_id

            self._trace('prepare_received', 
                        at_node=self.messenger.node_uid,
                        current_promised=str(self.promised_id))

            return (True, self.accepted_id, self.accepted_value)
        return (False, None, None)


    """
    phase 2:
    Handle ACCEPT_REQUEST messages from proposers.
    Args:
        from_uid: Proposer's node ID
        proposal_id: Proposal ID being accepted
        value: Value being proposed
    """
    def recv_accept_request(self, from_uid, proposal_id, value):
        if proposal_id >= self.promised_id:
            self.promised_id = proposal_id
            self.accepted_id = proposal_id
            self.accepted_value = value

            self._trace('accept_processed',
                        at_node=self.messenger.node_uid,
                        promised_id=str(self.promised_id))

            self.messenger.send_accepted(proposal_id, value)

    def _trace(self, event: str, **kwargs: any):
        """Helper for debug tracing."""
        self.messenger.debugger.trace_proposal(
            str(self.promised_id),
            self.accepted_value,
            event,
            **kwargs
        )

"""Implements the Learner role in Paxos consensus.
    Args:
        messenger: Communication handler
        quorum_size: Minimum responses needed for consensus
"""
class Learner:
    def __init__(self, messenger, quorum_size):
        # Configuration
        self.messenger = messenger
        self.quorum_size = quorum_size

        # Protocol state
        self.accepted_values = {}

    """
    phase 2:
    Handle ACCEPTED messages from acceptors.
    Args:
        from_uid: Acceptor's node ID
        proposal_id: Proposal ID being accepted
        value: Value being proposed
    """
    def recv_accepted(self, from_uid, proposal_id, value):
        if value not in self.accepted_values:
            self.accepted_values[value] = set()
        self.accepted_values[value].add(from_uid)


        self._trace('accept_received',
                    from_node=from_uid,
                    current_acceptors=list(self.accepted_values[value]),
                    quorum_needed=self.quorum_size)

        # Resolution logic
        self.messenger.on_resolution(value)
        del self.accepted_values[value]

    def _trace(self, event: str, **kwargs: any):
        """Helper for debug tracing."""
        self.messenger.debugger.trace_proposal(
            None,  # No proposal_id in learner
            None,  # No specific value
            event,
            **kwargs
        )