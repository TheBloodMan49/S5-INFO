#include "tache.h"
#include <stdio.h>
#include <stdlib.h>

void afficheTache(Tache* t){
    int i;
    printf("No : %d\n",t->no);
    printf("Duree : %d\n",t->duree);
    printf("NbPred : %d\n",t->nbPred);
    printf("Pred : ");
    for(i=0;i<t->nbPred;i++){
        printf("%d ",t->pred[i]);
    }
    printf("\nTitre : %s\n",t->titre);
}

Tache* lireTachesFichierDyn(char* nomFichier, int* nbTaches){
    FILE* f = NULL;
    int i=0, j;
    Tache* tab_t = NULL;

    f = fopen(nomFichier,"r");
    if(f==NULL){
        printf("Erreur d'ouverture du fichier\n");
        return NULL;
    }

    fscanf(f,"%d\n",nbTaches);

    tab_t = (Tache*) malloc(sizeof(Tache)*(*nbTaches));

    while(!feof(f)){
        fscanf(f,"%d %d %d ",&tab_t[i].no,&tab_t[i].duree,&tab_t[i].nbPred);
        for(j=0;j<tab_t[i].nbPred;j++){
            fscanf(f,"%d ",&tab_t[i].pred[j]);
        }
        fscanf(f,"%[^\n]\n", tab_t[i].titre);
        i++;
    }
    fclose(f);
    return tab_t;
}

void afficheTabTaches(Tache* tab_t, int nbTaches){
    int i;
    for(i=0;i<nbTaches;i++){
        afficheTache(&tab_t[i]);
        printf("----------------\n");
    }
}

int sommeTotalDuree(Tache* tab_t, int nbTaches){
    int somme=0, i;
    for(i=0;i<nbTaches;i++){
        somme+=tab_t[i].duree;
    }
    return somme;
}

int ecrireTachesFichier(char* nomFichier, Tache* tab_t, int nbTaches){
    FILE* f = NULL;
    int i,j;
    f = fopen(nomFichier,"w");
    if(f==NULL){
        printf("Erreur d'ouverture du fichier\n");
        return 1;
    }
    fprintf(f,"%d\n",nbTaches);
    for(i=0;i<nbTaches;i++){
        fprintf(f,"%d %d %d ",tab_t[i].no,tab_t[i].duree,tab_t[i].nbPred);
        for(j=0;j<tab_t[i].nbPred;j++){
            fprintf(f,"%d ",tab_t[i].pred[j]);
        }
        fprintf(f,"%s\n", tab_t[i].titre);
    }
    fclose(f);
    return 0;
}
