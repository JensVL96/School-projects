# Enable fundamental cacheability for distributed deep learning training

The problem of applying efficient I/O in deep learning:
	not cache friendly
		modified by developing with rank-based importance and designed a priority-based sampling

background
	three training categorizations:
		data-parallel
		model-parallel
		pipe line pretraining

### methods
	based on assumption you know (predict) future operations

control layer
	calculate the importance score associated with dat asamples
	samples with higher cross entropy should be prioritized
prioriy-based adaptive sampling (PADS)
	don't sample randomly
	samples apperas
		multinominal probability distribution
Adaptive priority-aware prediction (APP)


conclusion
shade realizes a DLT-aware caching policy which takes advantage of the fin-grained importance scores of data samples to enable a high level of data locality, and therefore, fundemantal cacheability for DLT-jobs

Evaluation demonstrastes that SHADE imprves the read hit ratio, thus significantly improving the DLT performance