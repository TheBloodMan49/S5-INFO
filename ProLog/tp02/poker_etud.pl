
% TP2 TERMES CONSTRUITS - A compléter et faire tourner sous Eclipse Prolog
% ==============================================================================
% ============================================================================== 
%	FAITS
% ============================================================================== 

  /*
	hauteur(Valeur)
*/
hauteur(deux).
hauteur(trois).
hauteur(quatre).
hauteur(cinq).
hauteur(six).
hauteur(sept).
hauteur(huit).
hauteur(neuf).
hauteur(dix).
hauteur(valet).
hauteur(dame).
hauteur(roi).
hauteur(as).

/*
	couleur(Valeur)
*/
couleur(trefle).
couleur(carreau).
couleur(coeur).
couleur(pique).

/*
	succ_hauteur(H1, H2)
*/
succ_hauteur(deux, trois).
succ_hauteur(trois, quatre).
succ_hauteur(quatre, cinq).
succ_hauteur(cinq, six).
succ_hauteur(six, sept).
succ_hauteur(sept, huit).
succ_hauteur(huit, neuf).
succ_hauteur(neuf, dix).
succ_hauteur(dix, valet).
succ_hauteur(valet, dame).
succ_hauteur(dame, roi).
succ_hauteur(roi, as).

/*
	succ_couleur(C1, C2)
*/
succ_couleur(trefle, carreau).
succ_couleur(carreau, coeur).
succ_couleur(coeur, pique).

/*
  carte_test
  cartes pour tester le prédicat EST_CARTE
*/

carte_test(c1,carte(sept,trefle)).
carte_test(c2,carte(neuf,carreau)).
carte_test(ce1,carte(7,trefle)).
carte_test(ce2,carte(sept,t)).

/*
  main_test
  main pour tester vos prédicats. 
  par exemple le but main_test(main_triee_brelan,X) vous permet de récupérer
  dans X une main triée qui contient un brelan.
*/

main_test(main_triee_une_paire, main(carte(sept,trefle), carte(valet,coeur), carte(dame,carreau), carte(dame,pique), carte(roi,pique))).
% attention ici m2 représente un ensemble de mains	 
main_test(m2, main(carte(valet,_), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(as,pique))).
main_test(main_triee_deux_paires, main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(roi,pique))).
main_test(main_triee_brelan, main(carte(sept,trefle), carte(dame,carreau), carte(dame,coeur), carte(dame,pique), carte(roi,pique))).	
main_test(main_triee_suite,main(carte(sept,trefle),carte(huit,pique),carte(neuf,coeur),carte(dix,carreau),carte(valet,carreau))).
main_test(main_triee_full,main(carte(deux,coeur),carte(deux,pique),carte(quatre,trefle),carte(quatre,coeur),carte(quatre,pique))).

main_test(merreur1, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle), carte(as,pique))).
main_test(merreur2, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle))).



% ============================================================================= 
%        QUESTION 1 : est_carte(carte(Hauteur,Couleur))
% ==============================================================================



% ==============================================================================
%	QUESTION 2 : est_main(main(C1,C2,C3,C4,C5))
%        faire les tests différents des que l on peut permet d élaguer l arbre et donc de gagner en temps d exécution .
%        (la phrase ci-dessus répond elle a l histoire du micro seconde dans l ennonce )
% ============================================================================== 



% ============================================================================= 
%       QUESTION 3  inf_carte(carte(_,_),carte(_,_))
% ============================================================================= 



% ============================================================================= 
%       QUESTION 4 : est_main_triee(main(C1,C2,C3,C4,C5))
% ============================================================================= 



% ============================================================================= 
%       QUESTION 5 : une_paire(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================== 



% ============================================================================= 
%       QUESTION 6 : deux_paires(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================= 




% ==============================================================================
%       QUESTION 7 : brelan(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ==============================================================================

   


% ============================================================================= 
%       QUESTION 8 : suite(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================= 




% ============================================================================= 
%       QUESTION 9 : full(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% =============================================================================




