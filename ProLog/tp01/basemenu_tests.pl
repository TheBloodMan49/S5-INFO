

% Tests

tests :-
    test(test_plat),
    test(test_repas),
    test(test_plat200_400),
    test(test_plat_bar),
    test(test_val_cal),
    test(test_repas_eq).

test_plat :-
    assert_true( plat(grillade_de_boeuf) ),
    assert_true( plat(saumon_oseille) ),
    assert_true( not (plat(artichauts_Melanie)) ),
    assert_true( not (plat(sorbet_aux_poires)) ).

test_repas :-
    assert_true( repas(cresson_oeuf_poche, poulet_au_tilleul, fraises_chantilly) ),
    assert_true( not (repas(melon_en_surprise, poulet_au_tilleul, fraises_chantilly)) ).

test_plat200_400 :-
    assert_true( sortall(P, plat200_400(P), [bar_aux_algues, poulet_au_tilleul, saumon_oseille]) ).

test_plat_bar :-
    assert_true( sortall(P, plat_bar(P), [grillade_de_boeuf, poulet_au_tilleul]) ).

test_val_cal :-
    assert_true( val_cal(cresson_oeuf_poche, poulet_au_tilleul, fraises_chantilly, 901) ),
    assert_true( not (val_cal(truffes_sous_le_sel, grillade_de_boeuf, sorbet_aux_poires, 901) )).

test_repas_eq :-
    assert_true( repas_eq(artichauts_Melanie, saumon_oseille, fraises_chantilly) ),
    assert_true( not (repas_eq(truffes_sous_le_sel, grillade_de_boeuf, sorbet_aux_poires)) ).

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



% Fin des tests.