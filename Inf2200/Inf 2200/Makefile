#Optimization levels GCC https://gcc.gnu.org/onlinedocs/gnat_ugn/Optimization-Levels.html

CC = gcc
CFLAGS = -pg -m32 -masm=att

all: program

main: main.c Makefile
	$(CC) $(CFLAGS) -O0 -S -o $@.s $<  

# asm.S and main has to exist before executing compilation targets
program: asm.S main
	$(CC) $(CFLAGS) -E $< > $@_pp.s
	$(CC) $(CFLAGS) main.s $@_pp.s -o $@