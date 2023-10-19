#include <stdio.h>
#include "tableau.h"

int main() {
    int tab[DIM_TAB];
    int histo_tab[VAL_MAX] = {0};
    init_alea_tab(tab);
    affiche_tab(tab, DIM_TAB);
    histo(tab, histo_tab);
    affiche_histo(histo_tab, 0);
    return 0;
}
