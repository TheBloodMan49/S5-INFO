#include <stdio.h>
#include <string.h>

int main(){
  char *chaine="bonjour 12";
  printf("%d : %s\n",strlen(chaine),strstr(chaine,"jour"));
  return 0;
}
