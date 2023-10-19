#include <stdio.h>

int min(int a, int b){		/* Fonction de calcul d’un minimum */
  if (a<b) return a;
  return b;
}
int main(){
  int (*ptf)(int a, int b);	/* Décl. d’une variable pointeur de fonction */
  ptf=min;
  printf("%d\n",(*ptf)(3,5));	/* Appel de la fonction min par le pointeur */
  return 0;
}
