
  
tests :-
    test(test_est_carte),
    test(test_est_main),
    test(test_inf_carte),
    test(test_est_main_triee),
    test(test_une_paire),
    test(test_deux_paires),
    test(test_brelan),
    test(test_suite),
    test(test_full).

test_tmp_carte(Y) :-
    carte_test(_,Y),
    est_carte(Y).

test_est_carte :-
     findall(Y,test_tmp_carte(Y),LY),assert_true(length(LY,2)). 

  % Prédicat permettant de récupérer toutes les mains définies dans la base de faits
test_tmp_est_main(Y):-
  main_test(_,Y),est_main(Y).
  
  % Prédicat vérifiant que 8 mains ont bien été récupérées
test_est_main :-
  findall(X,test_tmp_est_main(X),LX),assert_true(length(LX,8)).

test_inf_carte :-
    assert_true(inf_carte(carte(quatre, pique),carte(cinq,coeur))),
    assert_true(inf_carte(carte(quatre, coeur),carte(cinq,coeur))),
    assert_true(inf_carte(carte(quatre, carreau),carte(cinq,coeur))),
    assert_true(inf_carte(carte(quatre, trefle),carte(cinq,coeur))),
    assert_true(inf_carte(carte(cinq,trefle),carte(cinq,coeur))),
    findall(Y,inf_carte(carte(cinq,Y),carte(cinq,coeur)),LY),
    assert_true(length(LY,2)). 
  
test_tmp_m2_triee(X):-
    main_test(m2,X),
    est_main_triee(X).

test_est_main_triee :-
	main_test(main_triee_une_paire,Y),assert_true(est_main_triee(Y)),
    main_test(main_triee_deux_paires,Z),assert_true(est_main_triee(Z)),
	findall(X,test_tmp_m2_triee(X),LX),assert_true(length(LX,2)). 
  
test_tmp_deux_paires(Main):-
    main_test(_,Main),deux_paires(Main).

test_deux_paires:-
    findall(Y,test_tmp_deux_paires(Y),LY),assert_true(length(LY,3)). 

test_tmp_une_paire(Main):-
    main_test(_,Main),une_paire(Main).

test_une_paire:-
    findall(Y,test_tmp_une_paire(Y),LY),assert_true(length(LY,10)).

test_tmp_brelan(Main):-
    main_test(_,Main),brelan(Main).

test_brelan:-
    findall(Y,test_tmp_brelan(Y),LY),assert_true(length(LY,2)).

test_tmp_suite(Main):-
    main_test(_,Main),suite(Main).

test_suite:-
    findall(Y,test_tmp_suite(Y),LY),assert_true(length(LY,1)).

test_tmp_full(Main):-
    main_test(_,Main),full(Main).

test_full:-
  findall(Y,test_tmp_full(Y),LY),assert_true(length(LY,1)).


% Teste la propriete P et affiche ensuite "OK : P" ou "echec : P" 
test(P) :- P, !, printf("OK : %w \n", [P]).
test(P) :- printf("echec : %w \n", [P]), fail.

% Teste la propriete P et affiche ensuite "echec : P", ou rien si succès
assert_true(P) :- P, !.
assert_true(P) :- printf("echec : %w \n", [P]), fail.