
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int a,b;

    srand(time(NULL));
    a =  rand();
    b =  rand();
    printf("a = %d; b = %d \n",a,b);
}
/* Random, big numbers */
