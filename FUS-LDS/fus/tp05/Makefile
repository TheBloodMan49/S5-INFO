CC=gcc
SOURCES=principal.c tableau.c tri.c
OBJECTS=$(SOURCES:.c=.o)

principalR principalPO: CFLAGS:=-O3
principalG: CFLAGS:=-g
principalP principalPO: LFLAGS:=-pg

all: principal

include principal.d

principal principalG principalR principalP principalPO: $(OBJECTS)
	$(CC) $(LFLAGS) $^ -o $@


clean:
	rm -f *.o
	rm -f principal principalG principalR principalP principalPO
