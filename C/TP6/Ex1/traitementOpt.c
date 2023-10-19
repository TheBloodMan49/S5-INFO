#include "traitementOpt.h"
#include "commun.h"
#include <stdlib.h>
#include <stdio.h>


static int compter(Comptage* param, char * str){
  int res;
  char reste[TMAX];

  /* Gestion du nombre de noms détectés */
  if (str==NULL){
    int cpts=param->compteur;
    printf(param->message,param->compteur);
    param->compteur=0;
    return cpts;
  }
   /* Vérification du format de la ligne */
  res = sscanf(str,param->modele, reste); /* Récupère les noms */
  if (res==1){
    param->compteur++;
  }
  return res;
}

/* Fonction de comptage du nombre d’individus */
int compterIndividu(char * str){
  static Comptage param={" 0 @%*[^@]@ INDI%[ \r\n]",
                         "Nombre d'individus détectés: %d\n",0};
  return compter(&param,str);
}

int compterFemmes(char* str) {
    static Comptage param={" 1 SEX F%[ \r\n]",
                           "Nombre de femmes détectées: %d\n",0};
    return compter(&param,str);
}
