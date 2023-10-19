#include <stdio.h>
#include "tache.h"

int main() {
    int nbTaches;
    Tache tab_taches[MAXTACHES];
    nbTaches = lireTachesFichier("../taches.txt",tab_taches);
    afficheTabTaches(tab_taches,nbTaches);

    printf("Somme des durees : %d\n",sommeTotalDuree(tab_taches,nbTaches));

    ecrireTachesFichier("../tachesB.txt",tab_taches,nbTaches);
    return 0;
}