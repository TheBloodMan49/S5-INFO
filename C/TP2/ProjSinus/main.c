#include <stdio.h>
#include <math.h>

double puissance(double x, int n) {
    if (n == 0) {
        return 1;
    } else if (n > 0) {
        return x * puissance(x, n - 1);
    } else {
        return 1 / puissance(x, -n);
    }
}

int myfact(int n) {
    if (n < 0)
        return 0;
    else if (n == 0)
        return 1;
    else
        return n * myfact(n - 1);
}

double terme(double x, int n) {
    puissance(x, n) / myfact(n);
}

double sinus(double x, int n) {
    double res = 0;
    int i;
    double m = fmod(x, 2*M_PI);
    int signe = 1;
    for (i = 1; i < n; i+=2) {
        res += signe*terme(m, i);
        signe = -signe;
    }
    return res;
}

double suiv(double t, double x, int n) {
    return t * x*x / ((double) (n-1) * n);
}

double sinus2(double x, int n) {
    double m = fmod(x, 2*M_PI);
    double res = m, t = m;
    int i;
    int signe = -1;
    for (i = 3; i < n; i += 2) {
        t = suiv(t, m, i);
        res += t*signe;
        signe = -signe;
    }
    return res;
}

int main(void) {
    int i ;
    for (i = 1; i <= 41; i += 2) {
        printf("sinus(PI/2) au rang %d = %g \n", i, sinus( M_PI /2, i));
    }
    printf("sin(PI/2) = %g \n", sin(M_PI /2)) ;
    return 0;
}
