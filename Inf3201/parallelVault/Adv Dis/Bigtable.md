It is a distributed NoSQL database developed by google

Motivation of it: better scalability, fault tolerance, performance, efficiency

similar data close in memory
no joins, foreign keys or strict schemas

arch:
	chubby lock system
	data stored as disc as GFS
storage mode:
	commit log
	memtable
	SSTables
		Immutable files (no I/O op), indexed in memory 
transactions:
	doesn't support full acid transactions
		complicates arch
		reduce performance
	row level atomicity
role of master node:
	tablet assignment
	load balancing
	schema management
storage: SSTables and memtables
	memtables - in memory storage
	SStables sorted string tables
	handling deletes with tombstones
major and minor compactions:
	three types:
		minor
		merging (quick lookups)
		major
	benefits/challenges
		reduces read amplifications
handling reads and writes:
scalability: tablet splitting
	large -> split in two
	even load distribution (avoids bottlenecks)
network partition:
	occurs when nodes lose communication due to network failure
	can cause data inconsitencies if not handled properly
	bigTable solution -> chubby
		ensures only one active master node
CAP trade-offs
	prioritizes consistency and partition tolerance (not avaiability)
		sacrificed during chubby failures

## Performance evaluation

* random reads are slow
	* different lookpus in indexer
	* 
* write throughput declines
* load balancing is crucial

bigtable performance

* store in structure and a sorted format
* optimize read performance by minimizing 
* excells at handling larg estructure (overload latency, random access)

## negative

* chubby point of failure
* schema complexity
* no multi-row transactions

## conclusion
excels at handling large, structured datasets
optimized for fast scans, high write throughput, and large batch processing




tablet failure (by master)
* renew lock in chubby
* not renewed = crash
* reassigns tablet to 
* update meta with new sstablet to map to
* tell tablet new tablet assigned
* sstables loaded into tablet*

rows are distributed across several tables, so can't be updated simultaneously
control over locality of data?

multidimensional storage
	