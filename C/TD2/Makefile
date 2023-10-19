CC = gcc

CFLAGS = -Wall -Wextra -pedantic-errors -std=c90 -g
LIBS = -lm

# Directory for the binary output
BINDIR = bin

all: help

help:
	@echo "Usage: make compile FILE=<source_file.c>"

run: compile
	@./$(BINDIR)/$(FILE:.c=)

compile:
	@mkdir -p $(BINDIR)
	@$(CC) $(CFLAGS) -o $(BINDIR)/$(FILE:.c=) $(FILE) $(LIBS)

# Clean up the binary files
clean:
	@rm -rf $(BINDIR)

.PHONY: all help run compile clean
