#include <stdio.h>
#include "login.h"

int main() {
    char prenom[MAX_NOM+1];
    char nom[MAX_NOM+1];
    char id[MAX_ID+1];

    printf("Entrez votre prénom: ");
    scanf("%s", prenom);
    printf("Entrez votre nom: ");
    scanf("%s", nom);

    identifiant2(prenom, nom, id);
    printf("Votre identifiant est: %s\n", id);
    return 0;
}
