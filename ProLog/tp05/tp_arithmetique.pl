
% Q1

add1(zero, X, X).
add1(s(X), Y, s(Z)) :- 
  add1(X, Y, Z).

% Q2

sub1(X, zero, X).
sub1(s(X), s(Y), Z) :- 
  sub1(X, Y, Z).

% Q3

prod1(zero, _, zero).
prod1(s(X), Y, Z) :- 
  prod1(X, Y, W), 
  add1(W, Y, Z).

% Q4

factorial1(zero, s(zero)).
factorial1(s(X), Y) :- 
  factorial1(X, Z), 
  prod1(s(X), Z, Y).

% Q5


add2(X,Y,Z) :- 
  add2c(X,Y,Z,0).

add2c([],Y,Y,0).
add2c(X,[],X,0).
add2c(X,[],Z,1) :- 
  add2c(X,[1],Z,0).
add2c([],Y,Z,1) :- 
  add2c([1],Y,Z,0).

add2c([X|Xs],[Y|Ys],[Z|Zs],Cin) :-
  add_bit(X,Y,Cin,Z,Cout),
  add2c(Xs,Ys,Zs,Cout).

% Q6

sub2(X,Y,Z) :- 
  add2(Y,Z,X).

% Q7

prod2([],_,[]).
prod2(X,Y,Z) :- 
  sub2(X,[1],X1),
  prod2(X1,Y,W),
  add2(W,Y,Z).

% Q8

factorial2([], [1]).
factorial2(X, Y) :- 
  sub2(X, [1], X1),
  factorial2(X1, Z),
  prod2(X, Z, Y).

% Q9

factorial3(0, 1).
factorial3(X, Y) :- 
  W is X - 1,
  factorial3(W, Z),
  Y is X * Z.
