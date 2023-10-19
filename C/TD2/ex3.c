#include <stdio.h>
#include <stdlib.h>

#define FILE_MAX 32
#define NB_ELEVES_MAX 100

int main()
{
    float notes[NB_ELEVES_MAX];
    int i;
    int nb_notes;
    float moy;
    char nom_fichier[FILE_MAX];
    FILE* ptr_fic = NULL;

    /*lecture des notes dans le fichier*/
    printf("Tapez le nom du fichier :\n");
    scanf("%s", nom_fichier);

    /*ouverture du fichier*/
    ptr_fic = fopen(nom_fichier, "r");
    if (ptr_fic == NULL)
    {
        fprintf(stderr, "Pas de fichier trouvé");
        return 1;
    }

    /*lecture des notes dans le fichier*/
    nb_notes = 0;
    while (fscanf(ptr_fic, "%f", &notes[nb_notes]) == 1)
        nb_notes++;
    
    /*fermeture du fichier*/
    fclose(ptr_fic);

    /*afficher le tableau de notes sur une ligne*/
    printf("Tableau de notes :\n");
    for (i=0; i<nb_notes; i++) 
        printf("%.1f ", notes[i]);
    printf("\n");

    /*calcul de la moyenne*/
    moy = notes[0];
    for (i=1; i<nb_notes; i++)
        moy += notes[i];
    moy /= nb_notes;

    printf("Tapez le nom du fichier où écrire la moyenne :\n");
    scanf("%s", nom_fichier);

    /*ouverture du fichier*/
    ptr_fic = fopen(nom_fichier, "w+");
    if (ptr_fic == NULL)
    {
        fprintf(stderr, "Pas de fichier trouvé");
        return 1;
    }

    /*écriture du résultat dans un fichier*/
    fprintf(ptr_fic, "Moyenne : %f", moy);

    /*fermeture du fichier*/
    fclose(ptr_fic);

    return 0;
}
