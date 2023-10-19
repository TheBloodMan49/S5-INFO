#include <stdio.h>

int main(){
  int res; 				        /* Résultat des sscanf */
  char *chaine="bonjour 12";	/* Chaine à analyser */
  char ch1[50], ch2[50];	    /* Variables réceptacles des lectures*/
  int i;

  res=sscanf(chaine,"%s %d",ch1,&i);
  printf("%d : %d, %s\n",res,i,ch1);

  res=sscanf(chaine,"%d %s",&i,ch1);
  printf("%d : %d, %s\n",res,i,ch1);

  res=sscanf(chaine,"%[^ 0-9] %[0-9]",ch1,ch2);
  printf("%d : %s, %s\n",res,ch2,ch1);

  res=sscanf(chaine,"bon%[^ 0-9] 1%[0-9]",ch1,ch2);
  printf("%d : %s, %s\n",res,ch2,ch1);
  return 0;
}
