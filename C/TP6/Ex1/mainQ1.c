#include <stdio.h>

void compterSimple(){ int cpt=0; cpt++; printf("%d\n",cpt);}

void compterStatic(){ static int cpt=0; cpt++; printf("%d\n",cpt);}

int main(){
  compterSimple();
  compterSimple();
  compterSimple(); /* 3e appel de compterSimple */

  compterStatic();
  compterStatic();
  compterStatic(); /* 3e appel de compterStatic */

  return 0;
}
