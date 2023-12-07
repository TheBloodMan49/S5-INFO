membre(A,[A|_]).
membre(A,[_|X]) :- membre(A,X).

compte(_,[],0).
compte(A,[A|X],N) :-
  compte(A,X,M),
  N is M+1.
compte(A,[B|X],N) :-
  \==(A,B),
  compte(A,X,N).

renverser(A,B) :- renv(A,[],B).
renv([],A,A).
renv([X|A],B,R) :- renv(A,[X|B],R).

palind(X) :- renverser(X,X).

enieme(1,[A|_],A).
enieme(N,[_|X],A) :-
  N > 1, 
  M is N-1,
  enieme(M,X,A).

hors_de(_,[]).
hors_de(A,[B|X]) :- 
  \==(A,B),
  hors_de(A,X).

tous_diff([]).
tous_diff([A|X]) :-
  hors_de(A,X),
  tous_diff(X).

conc3([],[],L,L).
conc3([],[A|Y],Z,[A|T]) :-
  conc3([],Y,Z,T).
conc3([A|X],Y,Z,[A|T]) :-
  conc3(X,Y,Z,T).

debute_par(_,[]).
debute_par([A|X],[A|Y]) :-
  debute_par(X,Y).

sous_liste(X,Y) :- debute_par(X,Y).
sous_liste([_|X],Y) :- sous_liste(X,Y).

elim([],_).
elim([A|X],[A|Y]) :-
  hors_de(A,X),
  elim(X,Y).
elim([A|X],Y) :-
  membre(A,X),
  elim(X,Y).

inserer(E,[],[E]).
inserer(E,[E|L1],[E,E|L1]).
inserer(E,[A|L1],[E,A|L1]) :-
  E < A.
inserer(E,[A|L1],[A|L2]) :-
  E > A,
  inserer(E,L1,L2).

tri([],[]).
tri([A|X],Y) :-
  tri(X,Z),
  inserer(A,Z,Y).

/* Faux mais pas le temps */
enieme2(1,[A|_],A).
enieme2(N,[E|X],A) :-
  \==(E,A),
  enieme2(M,X,A),
  N is M+1.
