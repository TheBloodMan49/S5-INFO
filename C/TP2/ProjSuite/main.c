#include <stdio.h>
#include <math.h>

double suite(int n, int verbose) {
    double result;
    if (n == 0)
        result = M_E - 1;
    else if (n == 1)
        result = 1;
    else
        result = M_E - n*suite(n-1, verbose);
    if (verbose)
        printf("suite(%d) = %lf\n", n, result);
    return result;
}

double suiteDecroissante() {
    double result = 0;
    int i;
    for (i = 49; i >= 0; i--) {
        result = (M_E - result) / (i+1);
    }
    return result;
}

int main() {
    suite(20, 1);
    printf("suiteDecroissante() = %lf\n", suiteDecroissante());
    printf("M_E-1 = %lf\n", M_E-1);
    return 0;
}
