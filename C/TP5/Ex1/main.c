#include <stdio.h>
#include <stdlib.h>
#include "tache.h"
#include "liste.h"

int main() {

    int nbTaches;
    Tache* tab_taches = NULL;

    Liste l = NULL;
    int i;

    tab_taches = lireTachesFichierDyn("../taches.txt",&nbTaches);
    /*
    afficheTabTaches(tab_taches,nbTaches);

    printf("Somme des durees : %d\n",sommeTotalDuree(tab_taches,nbTaches));

    ecrireTachesFichier("../tachesB.txt",tab_taches,nbTaches);
    free((void*) tab_taches);
     */

    for (i=0; i<nbTaches; i++) {
        ajouttrie(&l, tab_taches[i], comparetitre);
    }

    afficheliste(l);

    free((void*) tab_taches);

    return 0;
}
