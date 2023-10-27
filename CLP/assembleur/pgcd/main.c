#include <stdio.h>

int pgcd (int a, int b) {
    if (a==0 || b==0) {
        return 0;
    } else if (a==b) {
        return a;
    } else if (a>b) {
        return pgcd(a-b, b);
    } else {
        return pgcd(a, b-a);
    }
}

int main() {
    int res = pgcd(5, 2);
    return 0;
}
