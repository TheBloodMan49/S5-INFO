/*
 *  Ent�te commune aux modules de l'application
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */
#ifndef COMMUN_H
#define COMMUN_H

// Si l'une des deux macros TYPE ou FORMAT n'est pas d�finie
#if  ! defined(TYPE) || ! defined(FORMAT)
// Effacement des macros (si beson)
#undef  TYPE
#undef  FORMAT
// D�finition du type entier par d�faut
#define TYPE int
#define FORMAT "%d"
#endif

// D�finition d'un type bool�en
typedef enum{FALSE=0, FAUX=0, TRUE=1, VRAI=1} Booleen;

#endif
