#include <stdio.h>
#include <math.h>

float norme(float x, float y) {
    return sqrt(x*x + y*y);
}

int main() {
    float x,y;
    printf("Entrez x et y : ");
    scanf("%f %f", &x, &y);

    printf("La norme est %f", norme(x,y));
    return 0;
}