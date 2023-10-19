#include <stdio.h>
#include <stdlib.h>

#define N 10

/*définition du type Action*/
typedef enum {AVANCER, TOURNERG, TOURNERD} Action;

/*définition du type Ordre*/
typedef struct {int temps; Action act;} Ordre;

/*définition du type Direction*/
typedef enum {NORD, OUEST, SUD, EST} Direction;

/*définition du type Mobile*/
typedef struct {int pos_i; int pos_j; Direction dir;} Mobile;

/*fonction d'affichage*/
void afficherMobile(Mobile m)
{
    printf("Le mobile est en %d %d\n", m.pos_i, m.pos_j);
}

int avancer(Mobile* p_m)
{
    switch (p_m->dir)
    {
        case NORD :
        {
            if (p_m->pos_i > 0)
                p_m->pos_i--;
            else return 0;
            break;
        }
        case SUD :
        {
            if (p_m->pos_i < (N-1))
                p_m->pos_i++;
            else return 0;
            break;
        }
        case EST :
        {
            if (p_m->pos_j < (N-1))
                p_m->pos_j++;
            else return 0;
            break;
        }
        case OUEST :
        {
            if (p_m->pos_j > 0)
                p_m->pos_j--;
            else return 0;
            break;
        }
        default : 
        {
            printf("Direction inconnue pour avancer\n");
            return 0;
        }
    }
    return 1;
}

int tournerGauche(Mobile* p_m)
{
    if (p_m->dir >= NORD && p_m->dir <= EST)
        p_m->dir = (p_m->dir+1)%4;
    else return 1;
    return 0;
}

int tournerDroite(Mobile* p_m)
{
    if (p_m->dir >= NORD && p_m->dir <= EST)
        p_m->dir = (p_m->dir-1+4)%4;
    else return 1;
    return 0;
}

int donnerOrdre(Ordre ord, Mobile* p_m)
{
    switch (ord.act)
    {
        case AVANCER : return avancer(p_m);
        case TOURNERG : return tournerGauche(p_m);
        case TOURNERD : return tournerDroite(p_m);
        default : printf("Ordre invalide"); return 1;
    }
}

int main()
{
    Ordre ordre0 = {0, AVANCER};
    Ordre ordre1 = {1, TOURNERG};
    Ordre ordre2 = {2, TOURNERD};
    Mobile mobileM = {0,1,EST};
    afficherMobile(mobileM);
    donnerOrdre(ordre0, &mobileM);
    afficherMobile(mobileM);
    donnerOrdre(ordre1, &mobileM);
    afficherMobile(mobileM);
    donnerOrdre(ordre2, &mobileM);
    afficherMobile(mobileM);
    return 0;
}
