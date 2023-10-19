/*!
 * \file tache.h
 * \brief Module de definition des taches
 *
 * \author  ...
 * \date ...
 *
 */

#ifndef TACHE_H_INC
#define TACHE_H_INC

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define LGMAX 64 /* longueur maximum du titre*/
#define NMAXPRED 16  /* nombre maximum de predecesseur*/
#define MAXTACHES 64 /* nombre maximum de taches*/

/*!
 * \struct Tache
 * Type representant une tache
 */
typedef struct{
    int no; /*le nom de la tache*/
    int duree; /* la duree de la tache*/
    int nbPred;     /* le nombre effectif de predecesseur de la tache*/
    int pred[NMAXPRED];  /*le tableau des predecesseurs*/
    char titre[LGMAX];   /*le titre de la tache*/
}Tache ;

void saisieTache(Tache* t);
void afficheTache(Tache* t);
int lireTachesFichier(char* nomFichier, Tache* tab_t);
void afficheTabTaches(Tache* tab_t, int nbTaches);
int sommeTotalDuree(Tache* tab_t, int nbTaches);
int ecrireTachesFichier(char* nomFichier, Tache* tab_t, int nbTaches);

#endif
