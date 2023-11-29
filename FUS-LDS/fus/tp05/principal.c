/*
 * Programme principal de test de gestion des tableaux, et des
 * algorithmes de tri correspondants
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */

#include <stdio.h>
#include "tri.h"

int main(int argc, char **argv){
  Tableau *pt;
  //int min=-505;
  //int max=123;

  // Indices extrêmes
#ifdef SIMPLE
  int min=-5;
  int max=7;
#else
  int min=-30500;
  int max=22300;
#endif

  // Valeurs extrêmes
  TYPE vmin=-500;
  TYPE vmax=1000;

  pt=allouerTableau(min, max);
  printf("Fin allocation\n");
  initialiserTableau(pt,vmin,vmax);
  printf("Fin initialisation ["FORMAT","FORMAT"]\n",vmin,vmax);
#ifdef SIMPLE
  afficherTableau(pt,"pt");
#endif
  triNaif(pt);
#ifdef SIMPLE
  afficherTableau(pt,"pt");
#endif
  return(0);
}
