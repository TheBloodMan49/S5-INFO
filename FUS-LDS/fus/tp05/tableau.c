/*
 * Gestion de tableaux s�curis�s avec des indices
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
 * Param�tres:
 *   min : indice minimum
 *   max : indice maximum
 * Retour:
 *   adresse du tableau allou�
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
 * Param�tres:
 *   pt  : adresse du tableau
 * Retour:
 *   indice minimum
 */
int getMinimum(Tableau *pt){return(pt->minimum);}

/*
 * Accesseur en lecture sur l'indice maximum
 * Param�tres:
 *   pt  : adresse du tableau
 * Retour:
 *   indice maximum
 */
int getMaximum(Tableau *pt){return(pt->maximum);}

/*
 * V�rification qu'un indice se trouve dans l'intervalle d�fini par le tableau
 * Param�tres:
 *   pt    : adresse du tableau
 *   indice: indice � tester
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
 * Param�tres:
 *   pt    : adresse du tableau
 *   indice: indice de la valeur � modifier
 *   valeur: valeur � mettre dans le tableau
 * Retour:
 *   TRUE si la valeur a �t� modifi�e
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
 * Param�tres:
 *   pt    : adresse du tableau
 *   indice: indice de la valeur � lire
 *   valeur: adresse o� mettre le r�sultat
 * Retour:
 *   TRUE si la valeur a �t� lue
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
 * Param�tres:
 *   pt    : adresse du tableau
 *   i     : indice de la premi�re case � inverser
 *   j     : indice de la seconde case � inverser
 * Retour:
 *   TRUE si l'inversion s'est bien pass�e
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
 * Param�tres:
 *   pt    : adresse du tableau
 *   vmin  : valeur minimum pr�ste dans le tableau
 *   vmax  : valeur maximum pr�ste dans le tableau
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
 * Affichage � l'�cran des valeurs d'un tableau
 * Param�tres:
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
