/*
 * Gestion de tableaux sécurisés avec des indices
 * compris entre un minimum et un maximum
 *
 * Auteur: Ivan Leplumey
 * Date  : 20.10.2008
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "tableau.h"

/*
 * Allocation d'un tableau
 * Paramètres:
 *   min : indice minimum
 *   max : indice maximum
 * Retour:
 *   adresse du tableau alloué
 */
Tableau *allouerTableau(int min, int max){
  if (min>max) return(NULL);
  Tableau *pt=(Tableau *)malloc(sizeof(Tableau));
  if (!pt)     return(NULL);
  pt->valeurs=(TYPE *)malloc((max-min+1)*sizeof(TYPE));
  if (!pt->valeurs){
    free(pt);
    return(NULL);
  }
  pt->minimum=min;
  pt->maximum=max;
	return(pt);
}

/*
 * Accesseur en lecture sur l'indice minimum
 * Paramètres:
 *   pt  : adresse du tableau
 * Retour:
 *   indice minimum
 */
int getMinimum(Tableau *pt){return(pt->minimum);}

/*
 * Accesseur en lecture sur l'indice maximum
 * Paramètres:
 *   pt  : adresse du tableau
 * Retour:
 *   indice maximum
 */
int getMaximum(Tableau *pt){return(pt->maximum);}

/*
 * Vérification qu'un indice se trouve dans l'intervalle défini par le tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   indice: indice à tester
 * Retour:
 *   TRUE si l'indice est valide
 */
static Booleen testIndice(Tableau *pt, int indice){
  if (indice<pt->minimum) return(FALSE);
  if (indice>pt->maximum) return(FALSE);
  return(TRUE);
}

/*
 * Changement d'une valeur du tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   indice: indice de la valeur à modifier
 *   valeur: valeur à mettre dans le tableau
 * Retour:
 *   TRUE si la valeur a été modifiée
 */
Booleen setValeur(Tableau *pt, int indice, TYPE valeur){
  if (testIndice(pt, indice)){
    pt->valeurs[indice-pt->minimum]=valeur;
    return(TRUE);
	}
	else return(FALSE);
}

/*
 * Lecture d'une valeur du tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   indice: indice de la valeur à lire
 *   valeur: adresse où mettre le résultat
 * Retour:
 *   TRUE si la valeur a été lue
 */
Booleen getValeur(Tableau *pt, int indice, TYPE *valeur){
  if (testIndice(pt, indice)){
    *valeur=pt->valeurs[indice-pt->minimum];
    return(TRUE);
	}
	else return(FALSE);
}

/*
 * Inversion de deux valeurs d'un tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   i     : indice de la première case à inverser
 *   j     : indice de la seconde case à inverser
 * Retour:
 *   TRUE si l'inversion s'est bien passée
 */
Booleen inverserValeurs(Tableau *pt, int i, int j){
  TYPE valeuri, valeurj;
  if (getValeur(pt, i, &valeuri)&&(getValeur(pt, j, &valeurj))){
		setValeur(pt, i, valeurj);
		setValeur(pt, j, valeuri);
    return(TRUE);
	}		
  else
    return(FALSE);
}

/*
 * Initialisation des valeurs d'un tableau
 * Paramètres:
 *   pt    : adresse du tableau
 *   vmin  : valeur minimum préste dans le tableau
 *   vmax  : valeur maximum préste dans le tableau
 */
void initialiserTableau(Tableau *pt, TYPE vmin, TYPE vmax){
	int i;
  TYPE valeur;
  for (i=pt->minimum; i<=pt->maximum; i++){
    valeur=(rand()/(double)RAND_MAX)*(vmax-vmin+1)+vmin;
    setValeur(pt,i,valeur);
	}
}

/*
 * Affichage à l'écran des valeurs d'un tableau
 * Paramètres:
 *   pt    : adresse du tableau
 */
void afficherTableau(Tableau *pt, char *nom){
	int i;
  printf("\nAffichage tableau %s [%d,%d]:\n",nom,pt->minimum,pt->maximum);
  printf("  Format d'affichage: %s","%s[%d]="FORMAT"\n");
  for (i=pt->minimum; i<=pt->maximum; i++)
		printf("\t%s[%d]="FORMAT"\n",nom,i,pt->valeurs[i-pt->minimum]);
  printf("\n");
}
