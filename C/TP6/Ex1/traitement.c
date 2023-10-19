#include <stdio.h>
#include <string.h>
#include "traitement.h"
#include "fichier.h"
#include "commun.h"

int lireNom(char * str){
  static int cpt=0;
  int res;
  char nom[TMAX];		/* Variable de récupération d’un nom */
  char reste[TMAX];	/* Variable de récupération des fins de ligne */

  /* Gestion du nombre de noms détectés */
if (str==NULL){
    int cpts=cpt;
    printf("Nombre de noms détectés: %d\n",cpt);
    cpt=0;
    return cpts;
  }

  /* Vérification du format de la ligne */
  /* Extraction des noms */
  res = sscanf(str," 1 NAME %*[^/\r\n]/%[^/]/%[ \r\n]", nom, reste);
  if (res==2){
    cpt++;
    printf("Nom : %s\n",nom);
  }
  else res = 0;
  return res;
}

int lireOccu(char * str){
    static int cpt=0;
    int res;
    char occu[TMAX];		/* Variable de récupération d’un nom */
    char reste[TMAX];	/* Variable de récupération des fins de ligne */

    /* Gestion du nombre de noms détectés */
    if (str==NULL){
        int cpts=cpt;
        printf("Nombre de professions détectées: %d\n",cpt);
        cpt=0;
        return cpts;
    }

    /* Vérification du format de la ligne */
    /* Extraction des noms */
    res = sscanf(str," 1 OCCU %[^\r\n]%[\r\n]", occu, reste);
    if (res==2){
        cpt++;
        printf("Profession : %s\n",occu);
    }
    else res = 0;
    return res;
}

