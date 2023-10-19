#include "tache.h"

void saisieTache(Tache* t){
    int i;
    printf("Saisie de la tache\n");
    printf("Numero de la tache : ");
    scanf("%d",&t->no);
    printf("Titre de la tache : ");
    scanf("%s",t->titre);
    printf("Duree de la tache : ");
    scanf("%d",&t->duree);
    printf("Nombre de predecesseurs : ");
    scanf("%d",&t->nbPred);
    for(i=0;i<t->nbPred;i++){
        printf("Predecesseur %d : ",i+1);
        scanf("%d",&t->pred[i]);
    }
}

void afficheTache(Tache* t) {
    int i;
    printf("Numero de la tache : %d\n",t->no);
    printf("Titre de la tache : %s\n",t->titre);
    printf("Duree de la tache : %d\n",t->duree);
    printf("Nombre de predecesseurs : %d\n",t->nbPred);
    for(i=0;i<t->nbPred;i++){
        printf("Predecesseur %d : %d\n",i+1,t->pred[i]);
    }
}

int lireTachesFichier(char* nomFichier, Tache* tab_t){
    FILE* f = NULL;
    int i=0, j;
    f = fopen(nomFichier,"r");
    if(f==NULL){
        printf("Erreur d'ouverture du fichier\n");
        return 1;
    }
    while(!feof(f)){
        fscanf(f,"%d %d %d ",&tab_t[i].no,&tab_t[i].duree,&tab_t[i].nbPred);
        for(j=0;j<tab_t[i].nbPred;j++){
            fscanf(f,"%d ",&tab_t[i].pred[j]);
        }
        fscanf(f,"%[^\n]\n", tab_t[i].titre);
        i++;
    }
    fclose(f);
    return i;
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
