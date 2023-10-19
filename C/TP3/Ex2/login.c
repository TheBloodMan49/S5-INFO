#include "login.h"
#include <string.h>
#include <ctype.h>

void identifiant(char* prenom, char* nom, char* id) {
    char nom_mod[MAX_NOM+1];
    id[0] = prenom[0];
    id[1] = '\0';
    strcpy(nom_mod, nom);
    nom_mod[MAX_ID-1] = '\0';
    strcat(id, nom_mod);
}

void identifiant2(char* prenom, char* nom, char* id) {
    char nom_mod[MAX_NOM+1];
    int i;
    id[0] = prenom[0];
    id[1] = '\0';
    strcpy(nom_mod, nom);
    nom_mod[MAX_ID-1] = '\0';
    strcat(id, nom_mod);
    for (i = 0; i < strlen(id); i++) {
        id[i] = tolower(id[i]);
    }
}
