% Tests

count(P, N) :-
    findall(_, P, R),
    length(R, N).

tests :-
    test( test_make_all_pairs_1 ),
    test( test_make_all_pairs_2 ),
    test( test_make_all_pairs_3 ),
    test( test_make_all_pairs_4 ),
    test( test_make_all_pairs_5 ),
    test( test_sub_list_1 ),
    test( test_sub_list_2 ),
    test( test_sub_list_3 ),
    test( test_sub_list_4 ),
    test( test_proposition1_1 ),
    test( test_proposition1_2 ),
    test( test_proposition1_3 ),
    test( test_proposition1_4 ),
    test( test_proposition2_1 ),
    test( test_proposition2_2 ),
    test( test_proposition2_3 ),
    test( test_proposition3_1 ),
    test( test_proposition3_2 ),
    test( test_proposition3_3 ),
    test( test_proposition4_1 ),
    test( test_proposition4_2 ),
    test( test_proposition4_3 ),
    test( test_proposition4_4 ),
    test( test_proposition4_5 ),
    test( test_proposition5_1 ),
    test( test_proposition5_2 ),
    test( test_proposition5_3 ),
    test( test_proposition5_4 ),
    test( test_proposition6_1 ),
    test( test_proposition6_2 ),
    test( test_proposition6_3 ),
    test( test_proposition6_4 ),
    test( test_proposition6_5 ),
    test( test_proposition7_1 ),
    test( test_proposition7_2 ),
    test( test_proposition7_3 ),
    test( test_proposition7_4 ),
    test( test_possible_worlds_1 ),
    test( test_possible_worlds_2 ),
    test( test_possible_worlds_3 ),
    test( test_possible_worlds_4 ),
    test( test_possible_worlds_5 ).


test_make_all_pairs_1 :-
    assert_true( make_all_pairs([], []) ).

test_make_all_pairs_2 :-
    assert_true( (make_all_pairs([1, 2], R),
                  msort(R, [likes(1, 1), likes(1, 2), likes(2, 1), likes(2, 2)])) ).

test_make_all_pairs_3 :-
    assert_true( (make_all_pairs([1, 1, 3, 4], R),
                  msort(R, [likes(1, 1), likes(1, 1), likes(1, 1), likes(1, 1), likes(1, 3), likes(1, 3),
                            likes(1, 4), likes(1, 4), likes(3, 1), likes(3, 1), likes(3, 3), likes(3, 4), likes(4, 1), likes(4, 1), likes(4, 3), likes(4, 4)])) ).

test_make_all_pairs_4 :-
    assert_true( (make_all_pairs([a, b, c, d], R),
                  msort(R, [likes(a, a), likes(a, b), likes(a, c), likes(a, d), likes(b, a), likes(b, b), likes(b, c), likes(b, d),
                            likes(c, a), likes(c, b), likes(c, c), likes(c, d), likes(d, a), likes(d, b), likes(d, c), likes(d, d)])) ).

test_make_all_pairs_5 :-
    assert_true( count(make_all_pairs([a, b, c, d], _), 1) ).

test_sub_list_1 :-
    assert_true( sub_list([], []) ).

test_sub_list_2 :-
    assert_true( sortall(R, sub_list([1, 2], R), [[], [1], [1, 2], [2]]) ).

test_sub_list_3 :-
    assert_true( sortall(R, sub_list([1, 2, 3], R), [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]) ).

test_sub_list_4 :-
    assert_true( count(sub_list([1, 2, 3, 5, 6, 7, 8], _), 128) ).

test_proposition1_1 :-
    assert_true( proposition1([likes(dana, cody)]) ).

test_proposition1_2 :-
    assert_true( not(proposition1([])) ).

test_proposition1_3 :-
    assert_true( not(proposition1([likes(dana, dana)])) ).

test_proposition1_4 :-
    assert_true( (proposition1(W),
                  member(likes(dana, cody), W)) ).

test_proposition2_1 :-
    assert_true( proposition2([likes(dana, cody)]) ).

test_proposition2_2 :-
    assert_true( not(proposition2([likes(bess, dana)])) ).

test_proposition2_3 :-
    assert_true( proposition2([]) ).

test_proposition3_1 :-
    assert_true( proposition3([likes(dana, cody), likes(cody, cody)]) ).

test_proposition3_2 :-
    assert_true( not(proposition3([likes(cody, abby)])) ).

test_proposition3_3 :-
    assert_true( proposition3([]) ).

test_proposition4_1 :-
    assert_true( proposition4([]) ).

test_proposition4_2 :-
    assert_true( not(proposition4([likes(cody, abby)])) ).

test_proposition4_3 :-
    assert_true( proposition4([likes(abby, abby)]) ).

test_proposition4_4 :-
    assert_true( proposition4([likes(abby, cody), likes(dana, bess), likes(cody, abby), likes(bess, dana)]) ).

test_proposition4_5 :-
    assert_true( not(proposition4([likes(abby, cody), likes(dana, bess), likes(cody, abby), likes(bess, dana), likes(bess, abby)])) ).

test_proposition5_1 :-
    assert_true( proposition5([likes(cody, bess), likes(dana, bess), likes(abby, cody), likes(abby, dana), likes(dana, dana)]) ).

test_proposition5_2 :-
    assert_true( proposition5([]) ).

test_proposition5_3 :-
    assert_true( not(proposition5([likes(cody, bess)])) ).

test_proposition5_4 :-
    assert_true( proposition5([likes(cody, cody)]) ).

test_proposition6_1 :-
    assert_true( proposition6([likes(cody, bess), likes(dana, bess), likes(abby, cody), likes(abby, dana), likes(dana, dana)]) ).

test_proposition6_2 :-
    assert_true( proposition6([]) ).

test_proposition6_3 :-
    assert_true( not(proposition6([likes(bess, dana), likes(bess, cody), likes(abby, cody)])) ).

test_proposition6_4 :-
    assert_true( not(proposition6([likes(bess, dana), likes(bess, cody), likes(dana, bess), likes(abby, dana), likes(abby, cody), likes(abby, abby)])) ).

test_proposition6_5 :-
    assert_true( proposition6([likes(bess, dana), likes(bess, cody), likes(dana, bess), likes(dana, dana), likes(dana, cody), likes(abby, abby)]) ).

test_proposition7_1 :-
    assert_true( not(proposition7([])) ).

test_proposition7_2 :-
    assert_true( proposition7([likes(abby, _), likes(cody, _), likes(bess, _), likes(dana, _)]) ).

test_proposition7_3 :-
    assert_true( not(proposition7([likes(cody, _), likes(bess, _), likes(dana, _)])) ).

test_proposition7_4 :-
    assert_true( not(proposition7([likes(cody, _), likes(bess, _), likes(dana, _), likes(cody, _)])) ).

test_possible_worlds_1 :-
    assert_true( (possible_worlds(W),
                  msort(W, W1),
                  W1 = [likes(abby, abby), likes(abby, bess), likes(abby, dana), likes(bess, abby),
                        likes(cody, cody), likes(cody, dana), likes(dana, abby), likes(dana, cody), likes(dana, dana)]) ).

test_possible_worlds_2 :-
    assert_true( (possible_worlds(W),
                  msort(W, W1),
                  W1 = [likes(abby, abby), likes(abby, bess), likes(abby, dana), likes(bess, abby),
                        likes(cody, cody), likes(cody, dana), likes(dana, abby), likes(dana, cody)]) ).

test_possible_worlds_3 :-
    assert_true( (possible_worlds(W),
                  msort(W, W1),
                  W1 = [likes(abby, abby), likes(abby, bess), likes(abby, dana), likes(bess, abby),
                        likes(cody, dana), likes(dana, abby), likes(dana, cody), likes(dana, dana)]) ).

test_possible_worlds_4 :-
    assert_true( (possible_worlds(W),
                  msort(W, W1),
                  W1 = [likes(abby, abby), likes(abby, bess), likes(abby, dana), likes(bess, abby),
                        likes(cody, dana), likes(dana, abby), likes(dana, cody)]) ).

test_possible_worlds_5 :-
    assert_true( count(possible_worlds(_), 4) ).

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

% abby bess cody dana

% dana likes cody
% bess does not like dana
% cody does not like abby
% nobody likes someone who does not like her
% abby likes everyone who likes bess
% dana likes everyone bess likes
% everybody likes somebody

people([abby, bess, cody, dana]).

% Questions 1.6 and 1.7
test_possible_worlds :-
    possible_worlds(World),
    writeln(World),
    fail.
