#ifndef FICHIER_H_INCLUDED
#define FICHIER_H_INCLUDED

#include <stdlib.h>
#include <stdio.h>

#include "commun.h" /*définition de TMAX*/

typedef enum{ARRET, RETOUR} TypeRetour;

FILE *ouvrirFichier(char *nom, char *mode, TypeRetour t);
void fermerFichier(FILE *f);
char *lireLigneFichier(FILE *f, char *tampon, int taille);
int traiterLignesFichier(FILE *f, int (*ptFonction)(char *));

#endif /* FICHIER_H_INCLUDED*/
