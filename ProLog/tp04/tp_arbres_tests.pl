/*
TP Listes Prolog

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/




% Tests

tests :-
     test( test_arbre_binaire ),
     test( test_dans_arbre_binaire ),
     test( test_sous_arbre_binaire ),
     test( test_remplacer ),
     test( test_isomorphes ),
     test( test_infixe ),    
 %    test( test_prefixe ),
 %    test( test_postfixe ),
     test( test_insertion_arbre_ordonne ),
     true.

 test_arbre_binaire :-
     assert_true( arbre_binaire(arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))) ),  % arbre_binaire(+B) : vérification B arbre binaire
     assert_true( not(arbre_binaire(arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, 7, vide)))) ). % arbre_binaire(+B)) : vérification B non arbre binaire

test_dans_arbre_binaire :-
     assert_true( dans_arbre_binaire(6,arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))) ), % dans_arbre_binaire(+E, +B) : vérification dans_arbre_binaire
     assert_true( not(dans_arbre_binaire(12, arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))))) ). % dans_arbre_binaire(+E, +B) : vérification absent 

test_sous_arbre_binaire :-
     assert_true( sous_arbre_binaire(arb_bin(4, vide, vide),arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(7, vide, vide), vide))) ),  % sous_arbre_binaire(+S, +B)  : vérification sous_arbre_binaire
     assert_true( not(sous_arbre_binaire(arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))),arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(8, vide,vide), vide)))) ),  % sous_arbre_binaire(+S, +B)  : vérification non sous_arbre_binaire
     assert_true( sous_arbre_binaire(arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)), arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide)))) ),  % sous_arbre_binaire(+S, +B)  : vérification sous_arbre_binaire
     assert_true( not(sous_arbre_binaire(arb_bin(4, vide, vide), arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide)))) ).  % sous_arbre_binaire(+S, +B)  : vérification non sous_arbre_binaire

test_remplacer :-
     assert_true( findall(B, remplacer(arb_bin(4, vide, vide), arb_bin(7, arb_bin(5, vide, vide), vide), arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide))), B), [arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(7, arb_bin(5, vide, vide), vide)), arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(7, arb_bin(5, vide, vide), vide)), arb_bin(10, vide, vide)))]) ).    % remplacer(+SA1, +SA2, +B, -B1)

test_isomorphes :-
     assert_true( isomorphes(arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide))), arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))) ), % isomorphes(+B1, +B2) : vérification isomorphes 
     assert_true( not(isomorphes(arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide))), arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide))))) ). % isomorphes(+B1, +B2) : vérification non isomorphes 

test_infixe :-
     assert_true( findall(L, infixe(arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))), L), [[6, 2, 1, 4, 3, 5]]) ).    % infixe(+B, -L)

test_prefixe :-
     assert_true( findall(L, prefixe(arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))), L), [[1, 2, 6, 3, 4, 5]]) ).    % prefixe(+B, -L)

test_postfixe :-
     assert_true( findall(L, postfixe(arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))), L), [[6, 2, 4, 5, 3, 1]]) ).    % postfixe(+B, -L)

    
test_insertion_arbre_ordonne :-
     assert_true( findall(B, insertion_arbre_ordonne(9,arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide)), B), [arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, arb_bin(9, vide, vide), vide), vide))]) ).    % insertion_arbre_ordonne(+X, +B1, -B2)



% SortedList donne la liste triee de toutes les solutions de Term dans le but Goal 
sortall(Term, Goal, SortedList) :-
    findall(Term, Goal, List),
    msort(List, SortedList).

% Teste la propriete P et affiche ensuite "OK : P" ou "echec : P" 
test(P) :- P, !, printf("OK : %w \n", [P]).
test(P) :- printf("echec : %w \n", [P]), fail.
% Teste la propriete P et affiche ensuite "echec : P", ou rien si succès
assert_true(P) :- P, !.
assert_true(P) :- printf("echec : %w \n", [P]), fail.
% Adaptation a Swish, utiliser format => assert_true(P) :- format('echec : ~p ~n', [P]), fail.

% Fin des tests


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Des arbres binaires à copier-coller dans vos tests
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

/* 

arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))

arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))


arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))

arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))

arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))

arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))

arb_bin(8, arb_bin(4, arb_bin(2, _, _), arb_bin(6, _, _)), arb_bin(12, arb_bin(10, _, _), _))

arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,vide,arb_bin(10,vide,vide)))

arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(6,vide,arb_bin(10,vide,vide)))

arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide)))

*/

