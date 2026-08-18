CC = gcc
CFLAGS = -m32 -Wall -Werror -Wno-unused -g -O2 -I.
OBJDIR = obj
SIMULATOR = cachesim
PROGRAM = test

OBJS = cpu.o memory.o
HEADERS = byutr.h memory.h

all: $(PROGRAM) $(SIMULATOR) $(HEADERS) Makefile

$(PROGRAM):
	$(CC) $(CFLAGS) mergesort.c -o $@

dirs:
	@mkdir -p $(OBJDIR)

$(SIMULATOR): $(patsubst %, $(OBJDIR)/%, $(OBJS))
	$(CC) $(CFLAGS) $^ -o $@ -lm

$(OBJDIR)/%.o: %.c dirs
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -rf *.o *~ $(SIMULATOR) $(OBJDIR)
