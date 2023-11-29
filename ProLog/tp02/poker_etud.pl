
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

est_carte(carte(H,C)) :- 
	hauteur(H), 
	couleur(C).

% ==============================================================================
%	QUESTION 2 : est_main(main(C1,C2,C3,C4,C5))
%        faire les tests différents des que l on peut permet d élaguer l arbre et donc de gagner en temps d exécution .
%        (la phrase ci-dessus répond elle a l histoire du micro seconde dans l ennonce )
% ============================================================================== 

est_main(main(C1,C2,C3,C4,C5)) :- 
	est_carte(C1), 
	est_carte(C2),
	est_carte(C3),
	est_carte(C4),
	est_carte(C5),
	\==(C1,C2),
	\==(C1,C3),
	\==(C1,C4),
	\==(C1,C5),
	\==(C2,C3),
	\==(C2,C4),
	\==(C2,C5),
	\==(C3,C4),
	\==(C3,C5),
	\==(C4,C5).

% ============================================================================= 
%       QUESTION 3  inf_carte(carte(_,_),carte(_,_))
% ============================================================================= 

inf_haut(H1,H2) :- succ_hauteur(H1,H2).
inf_haut(H1,H2) :- 
	succ_hauteur(H1,X),
	inf_haut(X,H2).

inf_coul(H1,H2) :- succ_couleur(H1,H2).
inf_coul(H1,H2) :- 
	succ_couleur(H1,X),
	inf_coul(X,H2).

inf_carte(carte(H1,_C1), carte(H2,_C2)) :- 
	inf_haut(H1,H2).
inf_carte(carte(H1,C1), carte(H2,C2)) :-
	==(H1,H2),
	inf_coul(C1,C2).

% ============================================================================= 
%       QUESTION 4 : est_main_triee(main(C1,C2,C3,C4,C5))
% ============================================================================= 

est_main_triee(main(C1,C2,C3,C4,C5)) :-
	est_main(main(C1,C2,C3,C4,C5)),
	inf_carte(C1,C2),
	inf_carte(C2,C3),
	inf_carte(C3,C4),
	inf_carte(C4,C5).

% ============================================================================= 
%       QUESTION 5 : une_paire(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================== 

un_egal(A,A,_,_,_).
un_egal(_,B,B,_,_).
un_egal(_,_,C,C,_).
un_egal(_,_,_,D,D).

une_paire(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))) :-
	est_main_triee(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))),
	un_egal(H1,H2,H3,H4,H5).

% ============================================================================= 
%       QUESTION 6 : deux_paires(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================= 

deux_egal(A,A,B,B,_) :-
	\==(A,B).
deux_egal(A,A,_,B,B) :-
	\==(A,B).
deux_egal(_,A,A,B,B) :-
	\==(A,B).

deux_paires(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))) :-
	est_main_triee(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))),
	deux_egal(H1,H2,H3,H4,H5).

% ==============================================================================
%       QUESTION 7 : brelan(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ==============================================================================

triplet(A,A,A,_,_).
triplet(_,A,A,A,_).
triplet(_,_,A,A,A).

brelan(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))) :-          
	est_main_triee(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))),       
	triplet(H1,H2,H3,H4,H5). 

% ============================================================================= 
%       QUESTION 8 : suite(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% ============================================================================= 

suite(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))) :-
	est_main_triee(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))),
	succ_hauteur(H1,H2),
	succ_hauteur(H2,H3),
	succ_hauteur(H3,H4),
	succ_hauteur(H4,H5).


% ============================================================================= 
%       QUESTION 9 : full(main(carte(_,_),carte(_,_),carte(_,_),carte(_,_),carte(_,_)))
% =============================================================================

triplet_paire(A,A,A,B,B) :-
	\==(A,B).
triplet_paire(A,A,B,B,B) :-
	\==(A,B).

full(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))) :-          
	est_main_triee(main(carte(H1,C1),carte(H2,C2),carte(H3,C3),carte(H4,C4),carte(H5,C5))),       
	triplet_paire(H1,H2,H3,H4,H5). 

