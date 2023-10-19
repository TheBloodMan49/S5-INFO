#include <stdio.h>
#include "fichier.h"
#include <string.h>
#include "traitement.h"
#include "traitementOpt.h"

int affichage(char *chaine){
  printf("%s",chaine);
  return 0;
}

int affichage2(char *chaine) {
    static int cpt=0;
    cpt++;
    printf("%d[%ld]\t: %s", cpt, strlen(chaine), chaine);
    return 0;
}

int main() {
  FILE * pFile=NULL;  /* Descripteur du fichier */

  pFile = ouvrirFichier("../exemple.ged","r",ARRET);
  traiterLignesFichier(pFile, compterFemmes);
  compterFemmes(NULL);
  fermerFichier(pFile);
  return 0;
}
