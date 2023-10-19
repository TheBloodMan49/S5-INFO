#include "tableau.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void init_alea_tab(int* tab) {
    int i;
    srand(time(NULL));
    for (i = 0; i < DIM_TAB; i++) {
        tab[i] = rand() % VAL_MAX;
    }
}

void affiche_tab(int* tab, int dim) {
    int i;
    for (i = 0; i < dim; i++) {
        printf("%d ", tab[i]);
    }
    printf("\n");
}

void histo(int* tab, int* histo_tab) {
    int i;
    for (i = 0; i < DIM_TAB; i++) {
        histo_tab[tab[i]]++;
    }
}

void affiche_histo(int* histo_tab, int show_zero) {
    int i;
    for (i = 0; i < VAL_MAX; i++) {
        char str[100] = "";
        int j;
        for (j = 0; j < histo_tab[i]; j++) {
            strcat(str, "-");
        }
        if (show_zero || histo_tab[i] != 0)
            printf("%d : %s\n", i, str);
    }
    printf("\n");
}
