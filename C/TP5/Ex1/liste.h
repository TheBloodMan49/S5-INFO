#ifndef LISTE_H
#define LISTE_H

#include "tache.h"

typedef struct struct_element {
    Tache t;
    struct struct_element* suivant;
} Element;

typedef Element* Liste;

void ajoutdeb(Liste* l, Tache t);
int nbelement(Liste l);
void afficheliste(Liste l);
void ajouttri(Liste* l, Tache t);

void ajouttrie(Liste*l, Tache t, int (*ptrfonc)(Tache, Tache));
int compareno(Tache t1, Tache t2);
int comparetitre(Tache t1, Tache t2);

#endif
