all front test doc cleanall clean cleandoc cleantest:
	cd src && $(MAKE) $@

.PHONY: doc cleanall clean cleandoc cleantest

run: cleanall all
	python3 src/make_table.py
	./run_front -c fill.dbcmd
	#./run_front
