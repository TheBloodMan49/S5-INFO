% 1)

is_sorted([]).
is_sorted([_]).
is_sorted([A,B|Rest]) :-
    A =< B,
    is_sorted([B|Rest]).

% 2)

% changed "merge" to "mergel" because of name conflict with built-in predicate

mergel([], Bs, Bs).
mergel(As, [], As).
mergel([A|As], [B|Bs], [A|Res]) :-
    A =< B,
    mergel(As, [B|Bs], Res).
mergel([A|As], [B|Bs], [B|Res]) :-
    A > B,
    mergel([A|As], Bs, Res).
  
% 3)

zip([], [], []).
zip([A|As], [B|Bs], [pair(A,B)|Res]) :-
    zip(As, Bs, Res).

% 4)

unzip([], [], []).
unzip([pair(A,B)|Rest], [A|As], [B|Bs]) :-
    unzip(Rest, As, Bs).

% 5)

insert(X, L, [X|L]).
insert(X, [E|L], [E|Res]) :-
    insert(X, L, Res).

% 6)

permutation([], []).
permutation([X|Xs], Ys) :-
    permutation(Xs, Zs),
    insert(X, Zs, Ys).

% 10)
% Knowledge base
femme(laurence).
homme(bertrand).
homme(yann).
collegues(laurence, yann).
collegues(yann, bertrand).
collegues(yann, pascal).
collegues(bertrand, eric).
collegues(eric, martine).
collegues(martine, patrice).
collegues(patrice, catherine).
collegues(catherine, yann).

temperature(pascal, 37).
temperature(yann, 38).

toux(bertrand).

odorat(eric, oui).

gout(laurence, moyen).
gout(yann, non).

% Actual question

individu(X) :- femme(X).
individu(X) :- homme(X).
individu(X) :- collegues(X, _).
individu(X) :- collegues(_, X).
individu(X) :- temperature(X, _).
individu(X) :- toux(X).
individu(X) :- odorat(X, _).
individu(X) :- gout(X, _).

% 11)

fievre(X) :- 
  temperature(X, Y), 
  Y >= 38.

% 12)

covid(X) :- fievre(X).
covid(X) :- toux(X).
covid(X) :- odorat(X, non).
covid(X) :- gout(X, non).

grippe(X) :-
  fievre(X),
  toux(X).

% 13)

travail(X) :- 
  individu(X),
  not(covid(X)),
  not(grippe(X)).

travaillent(L) :-
  setof(X, travail(X), L).

% 14)
% There is something wrong here
contamine(1, X, Y) :- collegues(X, Y).
contamine(1, X, Y) :- collegues(Y, X).
contamine(N, X, Y) :-
  N > 1,
  N1 is N - 1,
  contamine(N1, X, Y),
  X \= Y.
contamine(N, X, Y) :-
  N > 1,
  X \= Y,
  N1 is N - 1,
  contamine(N1, X, Z),
  contamine(1, X, Y).

contagion(X, N, L) :- 
  covid(X),
  setof(Y, contamine(N, X, Y), L).
contagion(X, _, []) :- 
  individu(X),
  not(covid(X)).
    
