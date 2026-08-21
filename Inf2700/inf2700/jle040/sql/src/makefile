cc = @gcc
cflags = -Wall -std=c11 -l sqlite3
sources = sql-json.c
sql-json = sql-json

$(sql-json):
	$(cc) $(sources) -o $@ $(cflags)

run: clean
	make $(sql-json)
	./$(sql-json) ../inf2700_orders.sqlite3

clean:
	rm -f $(sql-json)