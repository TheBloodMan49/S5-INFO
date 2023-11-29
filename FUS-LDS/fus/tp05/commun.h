/*
 *  Entête commune aux modules de l'application
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */
#ifndef COMMUN_H
#define COMMUN_H

// Si l'une des deux macros TYPE ou FORMAT n'est pas définie
#if  ! defined(TYPE) || ! defined(FORMAT)
// Effacement des macros (si beson)
#undef  TYPE
#undef  FORMAT
// Définition du type entier par défaut
#define TYPE int
#define FORMAT "%d"
#endif

// Définition d'un type booléen
typedef enum{FALSE=0, FAUX=0, TRUE=1, VRAI=1} Booleen;

#endif
