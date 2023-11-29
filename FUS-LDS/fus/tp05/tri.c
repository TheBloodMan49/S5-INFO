/*
 * Algorithmes de tri
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "tri.h"

/*
 * Accès à la valeur d'une case d'un tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   indice: indice de la case à lire
 * Retour:
 *   contenu de la case désignée
 */
static TYPE lireValeur(Tableau *pt, int indice){
  TYPE indiceValeur;
  if (!getValeur(pt,indice,&indiceValeur)){
    printf("[lireValeur] Erreur indice invalide - %d\n",indice);
    exit(1);
	}
	return(indiceValeur);
}

/*
 * Tri naïf d'un tableau
 * Paramètres:
 *   pt    : adresse du tableau
 */
void triNaif(Tableau *pt){
  int i, j;        // Indices de balayage du tableau
  int imin;        // Indice correspondant au minimum
  TYPE iminValeur; // Valeur minimum détectée

  int min=getMinimum(pt);
  int max=getMaximum(pt);
  for (i=min; i<max; i++){
    imin=i;
    iminValeur=lireValeur(pt, imin);
    for (j=i+1; j<=max; j++){
      if (iminValeur>lireValeur(pt,j)){
				imin=j;
				iminValeur=lireValeur(pt, imin);
			}
		}
    if (!inverserValeurs(pt,i,imin)){
      printf("[triNaif] Erreur inversion - %d<-> %d\n",i,imin);
      exit(1);
		}
	}
}
