#include <stdio.h>

int carre(int n)
{
    return n*n;
}

int main()
{
    int n;
    printf("Entrez un entier n : ");
    scanf("%d",&n);
    printf("Le carre de %d est %d\n",n,carre(n));
    return 0;
}