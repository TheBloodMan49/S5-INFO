
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int a,b;

    srand(time(NULL));
    a =  rand()%10;
    b =  rand()%10;
    printf("a = %d; b = %d \n",a,b);
}
/* Random numbers between 0 and 9 */
