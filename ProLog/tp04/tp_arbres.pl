
% Q1

arbre_binaire(vide).
arbre_binaire(arb_bin(R,G,D)) :- 
  integer(R),
  arbre_binaire(G),
  arbre_binaire(D).

% Q2

dans_arbre_binaire(E, arb_bin(R, _G, _D)) :- E == R.
dans_arbre_binaire(E, arb_bin(R, G, _D)) :-
  \==(E, R),
  dans_arbre_binaire(E, G).
dans_arbre_binaire(E, arb_bin(R, _G, D)) :- 
  \==(E, R),
  dans_arbre_binaire(E, D).

% Q3

sous_arbre_binaire(arb_bin(R, G, D), arb_bin(R, G, D)).
sous_arbre_binaire(arb_bin(R, G, D), arb_bin(R2, G2, _D2)) :-
  \==(R, R2),
  sous_arbre_binaire(arb_bin(R, G, D), G2).
sous_arbre_binaire(arb_bin(R, G, D), arb_bin(R2, _G2, D2)) :-
  \==(R, R2),
  sous_arbre_binaire(arb_bin(R, G, D), D2).

% Q4

remplacer(_, _, vide, vide).
remplacer(SA1, SA2, SA1, SA2).
remplacer(SA1, SA2, arb_bin(R, G, D), arb_bin(R, G2, D2)) :-
  \==(SA1, arb_bin(R, G, D)),
  remplacer(SA1, SA2, G, G2),
  remplacer(SA1, SA2, D, D2).

% Q5

isomorphes(vide, vide).
isomorphes(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
  R1 == R2,
  isomorphes(G1, G2),
  isomorphes(D1, D2).
isomorphes(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
  R1 == R2,
  isomorphes(G1, D2),
  isomorphes(D1, G2).

% Q6

infixe(vide, []).
infixe(arb_bin(R, G, D), L) :-
  infixe(G, L1),
  infixe(D, L2),
  append(L1, [R|L2], L).

% Q7

insertion_arbre_ordonne(E, vide, arb_bin(E, vide, vide)).
insertion_arbre_ordonne(E, arb_bin(R, G, D), arb_bin(R, G2, D)) :-
  E < R,
  insertion_arbre_ordonne(E, G, G2).
insertion_arbre_ordonne(E, arb_bin(R, G, D), arb_bin(R, G, D2)) :-
  E > R,
  insertion_arbre_ordonne(E, D, D2).
