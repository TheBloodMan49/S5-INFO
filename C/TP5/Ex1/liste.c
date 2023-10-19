#include "liste.h"
#include "tache.h"
#include <string.h>

void ajoutdeb(Liste* l, Tache t) {
    Element* e = (Element*) malloc(sizeof(Element));
    e->t = t;
    e->suivant = *l;
    *l = e;
}

int nbelement(Liste l) {
    int cpt = 0;
    Element* e = l;
    while (e != NULL) {
        cpt++;
        e = e->suivant;
    }
    return cpt;
}

void afficheliste(Liste l) {
    Element* e = l;
    while (e != NULL) {
        afficheTache(&e->t);
        e = e->suivant;
    }
}

void ajouttri(Liste* l, Tache t) {
    if (*l == NULL || t.no < (*l)->t.no) {
        ajoutdeb(l, t);
    } else {
        Element* e = *l;
        Element* n;
        while (e->suivant != NULL && e->suivant->t.no < t.no) {
            e = e->suivant;
        }
        n = (Element*) malloc(sizeof(Element));
        n->t = t;
        n->suivant = e->suivant;
        e->suivant = n;
    }
}

void ajouttrie(Liste*l, Tache t, int (*ptrfonc)(Tache, Tache)) {
    if (*l == NULL || ptrfonc(t, (*l)->t) < 0) {
        ajoutdeb(l, t);
    } else {
        Element* e = *l;
        Element* n;
        while (e->suivant != NULL && ptrfonc(e->suivant->t, t) < 0) {
            e = e->suivant;
        }
        n = (Element*) malloc(sizeof(Element));
        n->t = t;
        n->suivant = e->suivant;
        e->suivant = n;
    }
}

int compareno(Tache t1, Tache t2) {
    return t1.no - t2.no;
}

int comparetitre(Tache t1, Tache t2) {
    return strcmp(t1.titre, t2.titre);
}
