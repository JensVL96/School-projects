import time
# paxos.py
class ProposalID:
    def __init__(self, number, uid):
        self.number = number
        self.uid = uid

    def __lt__(self, other):
        if other is None:
            return False
        return (self.number < other.number) or (
            self.number == other.number and self.uid < other.uid
        )

    def __ge__(self, other):
        return not self.__lt__(other)
    
    def __hash__(self):
        return hash((self.number, self.uid))

    def __eq__(self, other):
        return self.number == other.number and self.uid == other.uid

    def __str__(self):
        return f"{self.number}-{self.uid}"

class Proposer:
    def __init__(self, messenger, proposer_uid, quorum_size):
        self.messenger = messenger
        self.proposer_uid = proposer_uid
        self.quorum_size = quorum_size
        self.proposal_id = ProposalID(0, proposer_uid)
        self.proposed_value = None
        self.promises_rcvd = set()
        self.sequence_number = 0
    
    def set_proposal(self, value):
        """
        Sets the proposal value for this node.
        """
        self.proposed_value = value
        self.prepare()

    def prepare(self):
        self.sequence_number = int(time.time() * 1000)
        self.proposal_id = ProposalID(self.sequence_number, self.proposer_uid)
        self.promises_rcvd = set()
        self.messenger.send_prepare(self.proposal_id)

    def recv_promise(self, from_uid, proposal_id, prev_accepted_id, prev_accepted_value):
        """
        Step 2: Proposer receives a PROMISE response from an acceptor.
        """
        if proposal_id != self.proposal_id or from_uid in self.promises_rcvd:
            return

        self.promises_rcvd.add(from_uid)

        if prev_accepted_id and prev_accepted_id > self.proposal_id:
            self.proposed_value = prev_accepted_value

        if len(self.promises_rcvd) == self.quorum_size:
            self.messenger.send_accept(self.proposal_id, self.proposed_value)

class Acceptor:
    def __init__(self, messenger):
        self.messenger = messenger
        self.promised_id = None
        self.accepted_id = None
        self.accepted_value = None

    def recv_prepare(self, from_uid, proposal_id):
        if self.promised_id is None or proposal_id > self.promised_id:
            self.promised_id = proposal_id
            self.messenger.send_promise(from_uid, proposal_id, self.accepted_id, self.accepted_value)

    def recv_accept_request(self, from_uid, proposal_id, value):
        if proposal_id is None:
            return
        if self.promised_id is None or proposal_id >= self.promised_id:
            self.promised_id = proposal_id
            self.accepted_id = proposal_id
            self.accepted_value = value
            self.messenger.send_accepted(proposal_id, value)

class Learner:
    def __init__(self, messenger, quorum_size):
        self.messenger = messenger
        self.quorum_size = quorum_size
        self.accepted_values = {}

    def recv_accepted(self, from_uid, proposal_id, value):
        if value not in self.accepted_values:
            self.accepted_values[value] = set()
        self.accepted_values[value].add(from_uid)

        self.messenger.on_resolution(value)

        # Clear tracking for this value to avoid duplicate resolutions
        del self.accepted_values[value]