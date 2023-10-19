/*!
 * \file  main.c
 * \brief  Fichier fourni avec des erreurs de syntaxe a copier et a corriger
 * \author Paul Gasnier
 * \date 17/08/2023
 * Objectif : comprendre la compilation, edition des liens et
              l'execution sous debogueur
            Une fonction main() unique qui fait :
            - la lecture de nombres reels ;
            - le calcul de la division de l'un par l'autre ;
            - l'affichage de resultats.
*/


#include <stdlib.h>	         /* EXIT_SUCCESS, system */
#include <stdio.h>           /* printf, scanf */


/*! \def NDIVISION
* \brief Le nombre de divisions calculées
*/
#define NDIVISION 3


/*!
 * \fn int main(void)
 *   Ce programme prend deux nombres en entrée et
 *   affiche le quotient de ces deux nombres
 * \return EXIT_SUCCESS si tout s'est bien passé
 */
int main(void)
{
    int i;
    double x,y,division;

    printf("Debut de l'execution...\n");
    printf("  Ce programme calcule %d division(s)\n" , NDIVISION);

    for(i=0; i<NDIVISION; i++)
    {
        /* Lecture de deux valeurs au clavier */
        fflush(stdin);
        printf("\tDonnez deux nombres : ");
        scanf("%lf %lf", &x, &y);

        /* Calcul du quotient*/
        division=x/y;

        /* Affichage des valeurs a l'ecran */
        printf("\t La division de %10.3f par %10.3f a pour valeur : %10.6f\n",x, y,division);
    }

    printf("... Fin de l'execution.\n");
#ifdef _WIN32
    system("PAUSE");
#endif
    return EXIT_SUCCESS;
} /* fin main */




