/*!
* \file main.c
* \brief Test du calcul de la factorielle
* \author Paul Gasnier
* \date 20/08/2023
*/
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

/*! \func int myfact(int n)
 *
 * @param n l'entier dont on veut calculer la factorielle
 * @return la factorielle de n
 */
int myfact(int n) {
    if (n < 0)
        return 0;
    else if (n == 0)
        return 1;
    else
        return n * myfact(n - 1);
}

int display_fact(unsigned int n) {
    int i;
    for (i = 1; i <= n; i++) {
        printf("Factorielle de %d : %d\n", i, myfact(i));
    }
}

int rang() {
    int n = 1;
    while (myfact(n) <= INT_MAX/(n+1)) {
        n++;
    }
    return n;
}

int main()
{
    /*
    int n;
    printf("Entrez un entier : ");
    scanf("%d", &n);
    display_fact(n);
    */
    printf("Le rang maximum pour le type int est : %d\n", rang());
    return EXIT_SUCCESS;
}
