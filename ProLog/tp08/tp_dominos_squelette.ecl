/*
TP Dominos

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/

% Quelques valeurs fournies de listes de dominos

stones1([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2)]).

stones2([stone(2, 2), stone(4, 6), stone(1, 2), stone(2, 4), stone(6, 2), stone(5, 1), stone(5, 5), stone(4, 5), stone(2, 3), stone(3, 6)]).

stones3([stone(6, 6), stone(6, 5), stone(6, 4), stone(6, 3), stone(6, 2), stone(6, 1), stone(6, 0),
         stone(5, 5), stone(5, 4), stone(5, 3), stone(5, 2), stone(5, 1), stone(5, 0),
         stone(4, 4), stone(4, 3), stone(4, 2), stone(4, 1), stone(4, 0),
         stone(3, 3), stone(3, 2), stone(3, 1), stone(3, 0),
         stone(2, 2), stone(2, 1), stone(2, 0),
         stone(1, 1), stone(1, 0),
         stone(0, 0)]).


% Prédicats fournis pour obtenir un affichage convivial des listes de chaines

% print_chains(+): chain list
% print_chains(Chains): prints each chain from Chains as a linear and uncompressed list of stones.

print_chains([]).
print_chains([C|Rest]) :-
    print1chain(C),
    print_chains(Rest).

print1chain(chain(L1,L2)) :-
    reverse(L2, L2R),
    append(L1, L2R, L),
    print1list(L),
    writeln("").

print1list([A,B]) :-
    print1domino(A,B).
print1list([A,B,C|Rest]) :-
    print1domino(A,B),
    print1list([B,C|Rest]).
    
print1domino(A,B) :-
    printf("[%w:%w]", [A, B]).


% choose(+, -, -): 'a list * 'a * 'a list
% choose(List, Element, Remaining) returns an Element from List and the Remaining list of elements.

choose([Elt|Rest], Elt, Rest).
choose([Elt|Rest], Chosen, [Elt|Rest2]) :-
    choose(Rest, Chosen, Rest2).

% ?- choose([1, 2, 3], Elt, Rest).
% Elt = 1
% Rest = [2, 3]
% Yes
% Elt = 2
% Rest = [1, 3]
% Yes
% Elt = 3
% Rest = [1, 2]
% Yes

% chains(+, +, -): stone list * chain list * chain list
% chains(Stones, Partial, Chains) computes Chains, the solution from Stones using the accumulator Partial.

chains([], Chains, Chains).
chains(Stones, Partial, Chains) :-
    choose(Stones, Stone, Stones2),
    placeStone(Stone, Partial, Partial2),
    chains(Stones2, Partial2, Chains).

placeStone(_, [], _) :- fail.
placeStone(stone(A, B), [chain([A|Rest1], Rest2)|Rest], [chain([B,A|Rest1], Rest2)|Rest]) :-
    A \= B.
placeStone(stone(A, B), [chain(Rest1, [A|Rest2])|Rest], [chain(Rest1, [B,A|Rest2])|Rest]) :-
    A \= B.
placeStone(stone(A, B), [chain([B|Rest1], Rest2)|Rest], [chain([A,B|Rest1], Rest2)|Rest]) :-
    A \= B.
placeStone(stone(A, B), [chain(Rest1, [B|Rest2])|Rest], [chain(Rest1, [A,B|Rest2])|Rest]) :-
    A \= B.
placeStone(stone(A, A), [chain([A|Rest1], Rest2)|Rest], [chain([A,A|Rest1], Rest2), chain([A],[double])|Rest]).

placeStone(stone(A, A), [chain(Rest1, [A|Rest2])|Rest], [chain(Rest1, [A,A|Rest2]), chain([A],[double])|Rest]).

placeStone(stone(A, B), [chain([C|Rest1], [D|Rest2])|Rest], [chain([C|Rest1], [D|Rest2])|X]) :-
    placeStone(stone(A, B), Rest, X).

    

% domino(+, -): stone list * chain list
% domino(Stones, Chains) computes Chains, the solution to the puzzle from Stones.

domino([Stone|Stones], Chains) :-
    placeFirstStone(Stone, FirstChain),
    chains(Stones, FirstChain, Chains).

placeFirstStone(stone(A,A), [chain([A],[A]), chain([A],[double])]).
placeFirstStone(stone(A,B), [chain([A],[B])]) :-
    A \= B.
