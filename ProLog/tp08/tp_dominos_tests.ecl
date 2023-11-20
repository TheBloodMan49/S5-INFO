% Tests

tests :-
    test( test_choose1 ),
    test( test_choose2 ),
    test( test_choose3 ),
    test( test_choose4 ),
    test( test_domino1 ),
    test( test_domino2 ),
    test( test_domino3 ),
    test( test_domino4 ),
    test( test_domino5 ),
    test( test_domino6 ),
    test( test_domino7 ),
    test( test_domino8 ).

test_choose1 :-
    assert_true( sortall((E, R), choose([], E, R), []) ).

test_choose2 :-
    assert_true( sortall((E, R), choose([1, 2, 3], E, R), [(1, [2, 3]), (2, [1, 3]), (3, [1, 2])]) ).

test_choose3 :-
    assert_true( sortall((E, R), choose([1, 2, 3, 4, 5, 6, 7], E, R), [(1, [2, 3, 4, 5, 6, 7]), (2, [1, 3, 4, 5, 6, 7]), (3, [1, 2, 4, 5, 6, 7]), (4, [1, 2, 3, 5, 6, 7]), (5, [1, 2, 3, 4, 6, 7]), (6, [1, 2, 3, 4, 5, 7]), (7, [1, 2, 3, 4, 5, 6])]) ).

test_choose4 :-
    assert_true( count(choose([1, 1, 1, 1, 1, 1, 1, 1, 1], _, _), 9) ).

test_domino1 :-
    assert_true( (domino([stone(2, 2), stone(1, 2)], R), msort(R, [chain([1, 2], [2]), chain([2], [double])])) ).

test_domino2 :-
    assert_true( not(domino([stone(2, 3), stone(1, 5)], _)) ).

test_domino3 :-
    assert_true( domino([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2), stone(5, 1), stone(5, 5), stone(4, 5), stone(2, 3), stone(3, 6)], _) ).

test_domino4 :-
    assert_true( 
        dominores([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2), stone(5, 1), stone(5, 5), stone(4, 5), stone(2, 3), stone(3, 6)], [chain([2, 6, 4, 2], 
        [double]), chain([4, 5], [double]), chain([5, 5, 1, 2], [6, 3, 2])]) 
    ).

test_domino5 :-
    assert_true(
        dominores([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2), stone(5, 1), stone(5, 5), stone(4, 5), stone(2, 3), stone(3, 6)], 
        [chain([2], [4, 5, 5, 1, 2]), chain([5], [double]), chain([6, 3, 2, 6, 4, 2], [double])])
    ).

test_domino6 :-
    assert_true(
        dominores([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2), stone(5, 1), stone(5, 5), stone(4, 5), stone(2, 3), stone(3, 6)], 
        [chain([4, 5], [double]), chain([2], [5, 5, 1, 2]), chain([6, 3, 2, 6, 4, 2], [double])])
    ).

test_domino7 :-
    assert_true(
        dominores([stone(6, 6), stone(6, 5), stone(6, 4), stone(6, 3), stone(6, 2), stone(6, 1), stone(6, 0),
         stone(5, 5), stone(5, 4), stone(5, 3), stone(5, 2), stone(5, 1), stone(5, 0),
         stone(4, 4), stone(4, 3), stone(4, 2), stone(4, 1), stone(4, 0),
         stone(3, 3), stone(3, 2), stone(3, 1), stone(3, 0),
         stone(2, 2), stone(2, 1), stone(2, 0),
         stone(1, 1), stone(1, 0),
         stone(0, 0)], 
        [chain([0, 0, 1, 4, 6], [double]), chain([1], [double]), chain([1, 1, 2], [double]), chain([1, 3, 3, 4], [double]), chain([2, 5, 1, 6, 2, 4, 4, 5], [double]), chain([4, 0], [double]), chain([5, 0, 2, 2, 3], [double]), chain([3, 6], [6, 0, 3, 5, 5, 6])])
    ).

test_domino8 :-
    assert_true(
        dominores([stone(6, 6), stone(6, 5), stone(6, 4), stone(6, 3), stone(6, 2), stone(6, 1), stone(6, 0),
         stone(5, 5), stone(5, 4), stone(5, 3), stone(5, 2), stone(5, 1), stone(5, 0),
         stone(4, 4), stone(4, 3), stone(4, 2), stone(4, 1), stone(4, 0),
         stone(3, 3), stone(3, 2), stone(3, 1), stone(3, 0),
         stone(2, 2), stone(2, 1), stone(2, 0),
         stone(1, 1), stone(1, 0),
         stone(0, 0)], 
        [chain([0], [double]), chain([1], [double]), chain([1, 1, 3, 3, 4], [double]), chain([1, 4, 6], [double]), chain([2, 5, 1, 6, 2, 4, 4, 5], [double]), chain([4, 0, 0, 1, 2], [double]), chain([5, 0, 2, 2, 3], [double]), chain([3, 6], [6, 0, 3, 5, 5, 6])])
    ).


count(P, N) :-
    findall(_, P, R),
    length(R, N).
    
canonize([], []).
canonize([chain(X, Y) | R], [chain(X1, Y1) | R1]) :-
    msort([X, Y], [X1, Y1]),
    canonize(R, R1).

% CanonizedChains est une version triée canonique des chaînes du résultat pour pouvoir comparer au résultat attendu.
dominores(Stones, CanonizedChains) :-
	domino(Stones, Chains),
	msort(Chains,SortedChains),
	canonize(SortedChains, CanonizedChains).

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
