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


% domino(+, -): stone list * chain list
% domino(Stones, Chains) computes Chains, the solution to the puzzle from Stones.
