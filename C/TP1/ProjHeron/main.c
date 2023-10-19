#include <stdio.h>
#include <math.h>

/* Implement the heron algorithm */
float racine(float n, float precision)
{
    float u;
    float unext = n/2;
    do
    {
        u = unext;
        unext = (u + n/u) / 2;
    } while (fabs(unext - u) > precision);
    return u;
}

int main()
{
    float n, precision;
    printf("Entrez un nombre n et la precision du calcul : ");
    scanf("%f %f", &n, &precision);
    printf("Valeur de racine(%f) : %f\n",n,racine(n, precision));
    printf("Valeur de sqrt(%f) : %f\n",n,sqrt(n));
    return 0;
}