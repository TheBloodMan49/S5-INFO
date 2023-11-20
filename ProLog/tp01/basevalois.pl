/**
TP Base Valois - Famille de France

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/

% Tests

% Teste la propriete P et affiche ensuite "OK : P" ou "echec : P" 
test(P) :- P, !, printf("OK : %w \n", [P]).
test(P) :- printf("echec : %w \n", [P]), fail.

% Teste la propriete P et affiche ensuite "echec : P", ou rien si succès
assert_true(P) :- P, !.
assert_true(P) :- printf("echec : %w \n", [P]), fail.

tests :-
    test(test_enfant),
    test(test_parent),
    test(test_grand_pere),
    test(test_frere),
    test(test_oncle),
    test(test_cousin),
    test(test_le_roi_est_mort_vive_le_roi),
    test(test_ancetre).

test_enfant :-
    assert_true( enfant(claude_de_france, louis_XII) ),
    assert_true( enfant(charles_VII, charles_VI) ),
    assert_true( not enfant(charles_VIII, charles_VI) ),
    assert_true( not enfant(valentine_de_milan, charles_VII) ).

test_parent :-
    assert_true( parent(louis_XII, claude_de_france) ),
    assert_true( not parent(anne_de_Bretagne, francois_I) ),
    assert_true( sortedof(P, parent(P,louis_d_Orleans), [charles_V, jeanne_de_Bourbon]) ).

test_grand_pere :-
    assert_true( grand_pere(louis_d_Orleans, charles_d_angouleme) ),
    assert_true( not grand_pere(louis_XI, charles_d_angouleme) ),
    assert_true( sortedof(E, grand_pere(louis_d_Orleans,E), [charles_d_angouleme, louis_XII]) ).

test_frere :-
    assert_true( frere(francois_II, charles_IX) ),
    assert_true( sortedof(F, frere(charles_IX, F), [francois_II, henri_III]) ).

test_oncle :-
    assert_true( oncle(charles_VI, jean_d_angouleme) ),
    assert_true( not oncle(louis_d_Orleans, louis_XII) ),
    assert_true( sortedof(N, oncle(charles_VI, N), [charles_d_Orleans, jean_d_angouleme]) ).

test_cousin :-
    assert_true( sortedof(C, cousin(charles_VII, C), [charles_d_Orleans, jean_d_angouleme]) ),
    assert_true( not cousin(charles_IX, henri_III) ),
    assert_true( not cousin(charles_d_Orleans, louis_d_Orleans) ).

test_le_roi_est_mort_vive_le_roi :-
    assert_true( le_roi_est_mort_vive_le_roi(charles_VI, 1422,charles_VII) ),
    assert_true( not le_roi_est_mort_vive_le_roi(charles_VI, 1421,charles_VII) ).


test_ancetre :-
    assert_true( sortedof(A, ancetre(A, louis_d_Orleans), [bonne_de_luxembourg, charles_V, charles_de_Valois, jean_II, jeanne_de_Bourbon, jeanne_de_Bourgogne, philippe_VI]) ).



sortedof(Term, Goal, SortedList) :-
    findall(Term, Goal, List),
    msort(List, SortedList).

test(P) :- P, !, printf("OK %w \n", [P]).
test(P) :- printf("echec %w \n", [P]), fail.

% Fin des tests.

homme(charles_V).
homme(charles_VI).
homme(charles_VII).
homme(louis_XI).
homme(charles_VIII).
homme(louis_XII).
homme(francois_I).
homme(henri_II).
homme(francois_II).
homme(charles_IX).
homme(henri_III).
homme(jean_II).
homme(philippe_VI).
homme(charles_d_Orleans).
homme(charles_de_Valois).
homme(louis_d_Orleans).
homme(jean_d_angouleme).
homme(charles_d_angouleme).

femme(anne_de_cleves).
femme(louise_de_Savoie).
femme(claude_de_france).
femme(anne_de_Bretagne).
femme(catherine_de_medicis).
femme(charlotte_de_Savoie).
femme(marie_d_anjou).
femme(isabeau_de_Baviere).
femme(valentine_de_milan).
femme(jeanne_de_Bourbon).
femme(bonne_de_luxembourg).
femme(jeanne_de_Bourgogne).
femme(marie_Stuart).
femme(elisabeth_d_autriche).
femme(louise_de_lorraine).
femme(marguerite_de_Rohan).

mere(marguerite_de_Rohan, charles_d_angouleme).
mere(jeanne_de_Bourgogne, jean_II).
mere(bonne_de_luxembourg, charles_V).
mere(jeanne_de_Bourbon, charles_VI).
mere(jeanne_de_Bourbon, louis_d_Orleans).
mere(valentine_de_milan, charles_d_Orleans).
mere(valentine_de_milan, jean_d_angouleme).
mere(isabeau_de_Baviere, charles_VII).
mere(marie_d_anjou, louis_XI).
mere(charlotte_de_Savoie, charles_VIII).
mere(anne_de_Bretagne, claude_de_france).
mere(claude_de_france, henri_II).
mere(anne_de_cleves, louis_XII).
mere(louise_de_Savoie, francois_I).
mere(catherine_de_medicis, francois_II).
mere(catherine_de_medicis, charles_IX).
mere(catherine_de_medicis, henri_III).

epoux(marguerite_de_Rohan, jean_d_angouleme).
epoux(louise_de_lorraine, henri_III).
epoux(elisabeth_d_autriche, charles_IX).
epoux(marie_Stuart, francois_II).
epoux(jeanne_de_Bourgogne, philippe_VI).
epoux(bonne_de_luxembourg, jean_II).
epoux(jeanne_de_Bourbon, charles_V).
epoux(valentine_de_milan, louis_d_Orleans).
epoux(isabeau_de_Baviere, charles_VI).
epoux(marie_d_anjou, charles_VII).
epoux(charlotte_de_Savoie, louis_XI).
epoux(catherine_de_medicis, henri_II).
epoux(anne_de_cleves, charles_d_Orleans).
epoux(louise_de_Savoie, charles_d_angouleme).
epoux(claude_de_france, francois_I).
epoux(anne_de_Bretagne, charles_VIII).
epoux(anne_de_Bretagne, louis_XII).
epoux(H,F) :- homme(H), femme(F), epoux(F,H).

pere(louis_XII, claude_de_france).
pere(charles_de_Valois, philippe_VI).
pere(philippe_VI, jean_II).
pere(jean_II, charles_V).
pere(charles_V, charles_VI).
pere(charles_VI, charles_VII).
pere(charles_VII, louis_XI).
pere(charles_d_Orleans, louis_XII).
pere(charles_d_angouleme, francois_I).
pere(francois_I, henri_II).
pere(henri_II, francois_II).
pere(henri_II, charles_IX).
pere(henri_II, henri_III).
pere(louis_d_Orleans, charles_d_Orleans).
pere(charles_V, louis_d_Orleans).
pere(jean_d_angouleme, charles_d_angouleme).
pere(louis_d_Orleans, jean_d_angouleme).

roi(charles_V, le_sage, 1364, 1380).
roi(charles_VI, le_bien_aime, 1380, 1422).
roi(charles_VII, xx, 1422, 1461).
roi(louis_XI, xx, 1461, 1483).
roi(charles_VIII, xx, 1483, 1498).
roi(louis_XII, le_pere_du_peuple, 1498, 1515).
roi(francois_I, xx, 1515, 1547).
roi(henri_II, xx, 1547, 1559).
roi(francois_II, xx, 1559, 1560).
roi(charles_IX, xx, 1560, 1574).
roi(henri_III, xx, 1574, 1589).
roi(jean_II, le_bon, 1350, 1364).
roi(philippe_VI, de_valois, 1328, 1350).

/* Q2.1 */
parent(P,E) :- pere(P,E).
parent(P,E) :- mere(P,E).

enfant(E,P) :- parent(P,E).

grand_pere(G,N) :- 
    homme(G), 
    pere(G,X), 
    parent(X,N).

frere(F,N) :-
    homme(F),
    pere(X,F),
    pere(X,N),
    F \== N.

oncle(O,N) :-
    homme(O),
    frere(O,X),
    parent(X,N).

cousin(C,N) :-
    parent(X,C),
    parent(Y,N),
    frere(X,Y).

le_roi_est_mort_vive_le_roi(R1,D,R2) :-
    roi(R1,_,_,D),
    roi(R2,_,D,_).

/* Q2.2 */
ancetre(X,Y) :-
    parent(X,Y).
ancetre(X,Y) :-
    parent(X,Z),
    ancetre(Z,Y).
