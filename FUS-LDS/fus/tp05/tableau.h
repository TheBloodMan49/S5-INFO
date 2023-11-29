/*
 * Entête correspondant à la structure de tableau
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */

#ifndef TABLEAU_H
#define TABLEAU_H

#include "commun.h"

typedef struct {
  int minimum;   // Indice minimum du tableau
  int maximum;   // Indice maximum du tableau
  TYPE *valeurs; // Tableau de valeurs
} Tableau;

Tableau *allouerTableau(int min, int max);
void initialiserTableau(Tableau *pt, TYPE vmin, TYPE vmax);
void afficherTableau(Tableau *pt, char *nom);

int getMinimum(Tableau *pt);
int getMaximum(Tableau *pt);
Booleen setValeur(Tableau *pt, int indice, TYPE valeur);
Booleen getValeur(Tableau *pt, int indice, TYPE *valeur);
Booleen inverserValeurs(Tableau *pt, int i, int j);

#endif

