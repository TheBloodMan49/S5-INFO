#ifndef TABLEAU_H
#define TABLEAU_H

#include <stdlib.h>
#include <time.h>

#define DIM_TAB 5
#define VAL_MAX 20

void init_alea_tab(int* tab);
void affiche_tab(int* tab, int dim);
void histo(int* tab, int* histo_tab);
void affiche_histo(int* histo_tab, int show_zero);
#endif