/***********************************************************************
*                                                                      *
*            Programme principal de la simulation de stockage          *
*                                                                      *
***********************************************************************/

#include <stdio.h>

a
 a
  a
   a
    a
     a
      a
       a
        a
         a
          a

begin
\begin
\\begin
\\\begin

main()
{
					/* declaration du prog. pr.   */

	int j,i,k;
		
	int nb_i;
	int nb_p;
	int nb_o;
	double v_i[10];
	double v_p[10];
	double v_o[10];

	int kesten[10];
	int ikesten[10];
	double gamma[10];

	int marche = 1;












/*********************************************************************/
/*                                                                   */
/*              Initialisation  des parametres                       */
/*                                                                   */
/*********************************************************************/
					/* initialisation des para- */
					/* metres de commandes      */

					/* initialisation des para- */
					/* metres d'approximations  */
	nb_i = 4;
	v_i[0] = 200.;			/* RMIN			    */
	v_i[1] = 700.;			/* Q			    */
	v_i[2] = 70.;			/* T    		    */
	v_i[3] = 300.;			/* MU			    */

	nb_p = 6;
	v_p[0] = 200.;			/* A			    */
	v_p[1] = 100.;			/* j			    */
	v_p[2] = 0.1;			/* I			    */
	v_p[3] = 30.;			/* C			    */
	v_p[4] = 80.;			/* PI			    */
	v_p[5] = 8.;			/* PIPR			    */

	nb_o = 2;
	
	gamma[0] = 10.;
	gamma[1] = 7.50;
		
	kesten[0] = 0;
	kesten[1] = 0;
		
	ikesten[0] = 1;
	ikesten[1] = 1;

  lab3 :    				/* exemple de l'etiquette  */
	while(marche){marche--;}
					/*
					    ALORS LA
ALORS LA
								   */
		
/*********************************************************************/
/*                                                                   */
/*            etape adaptative, traitement de l'echantillon          */
/*                                                                   */
/*********************************************************************/
	for ( i = 1 ; i <= 500 ; i++ )
		{			/* mise en marche des para-  */
					/* metres de controle        */ 
		if (D_SimrQ_2 (nb_i,v_i,nb_p,v_p,nb_o,v_o))
			{
			if ( kesten[0] && (v_o[0] < 0.0))
				{
				ikesten[0]++;
				kesten[0] = 0;
				}
			if ( kesten[1] && (v_o[1] < 0.0))
				{
				ikesten[1]++;
				kesten[1] = 0;
				}
			printf(" %g  %g * " ,v_o[0],v_o[1]);
			printf(" %g  %g * " ,gamma[0]/ikesten[0],
			              gamma[1]/ikesten[1]);
			v_i[0] = v_i[0] - gamma[0]/ikesten[0] * v_o[0];
			v_i[1] = v_i[1] - gamma[1]/ikesten[1] * v_o[1];
		v_i[2] = v_i[1]/10.;	/*	 T = Q/lambda	    */
			printf(" :  %g  %g \n" ,v_i[0],v_i[1]);
			}
		else
			printf ("probleme de l'appel\n");
		}
/*********************************************************************/
/*                                                                   */
/*                   etape des jeux d'essai                          */
/*                                                                   */
/*********************************************************************/

	while (marche)

		{			/* mise en marche des para-  */
					/* metres de control         */ 
		Param(kesten, v);

					/* simulation de stockage    */
		Simul (nb_i, nb_p, nb_o, v_i, v_p, v_o);

		}
}
bibelot
	 

