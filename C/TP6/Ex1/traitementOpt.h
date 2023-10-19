#ifndef TRAITEMENT_OPT_INC
#define TRAITEMENT_OPT_INC

typedef struct {
    char * modele ;
    char * message ;
    int compteur ;
} Comptage ;

int compterIndividu(char* str);
int compterFemmes(char* str);


#endif /* TRAITEMENT_OPT_INC */