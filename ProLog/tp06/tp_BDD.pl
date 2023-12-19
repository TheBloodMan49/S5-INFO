/**
TP 6 Base de Données Déductives (BDD) - Prolog

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/


% ============================================================================= 
% Tests automatisés
% ============================================================================= 
     
tests :-
	test( test_selection_lyon ),
	test( test_projection ),
	test( test_union ),
	test( test_intersection ),
	test( test_difference ),
	test( test_produit_cartesien ),	
	test( test_jointure ),
	test( test_jointure_sup ),
	test( test_division ),
	test( test_total_pieces_livrees_fournisseur ),
	test( test_est_compose_de ),
	test( test_nb_pieces_tot ),
	test( test_nb_voiture ),
	true.


test_selection_lyon :-
        assert_true(sortall([NumPiece,Nom], selection_lyon(NumPiece,Nom),[[p1,tole], [p2,jante]])).

test_projection :-
        assert_true(sortall([Nom,Lieu], projection(Nom,Lieu),[[jante, lyon], [jante, marseille], [piston, toulouse], [pneu, clermontFerrand], [soupape, lille], [tole, lyon], [tole, marseille], [vitre, marseille], [vitre, nancy]])).

test_union :-
        assert_true(sortall([Nom,Ville], union(Nom, Ville),[[brown, marseille], [dupond, lille], [dupont, lyon], [durand, lille], [martin, rennes], [michel, clermontFerrand], [smith, paris]])).
 
test_intersection :-
        assert_true(sortall([Nom,Ville], intersection(Nom, Ville),[[brown, marseille], [dupont, lyon], [durand, lille], [martin, rennes], [michel, clermontFerrand], [smith, paris]])).

test_difference :-
        assert_true(sortall([Nom,Ville], difference(Nom, Ville),[[dupond, lille]])).

test_produit_cartesien :-
        assert_true(sortall([NumF1, Nom, marseille, NumF2, Piece, Quantite], produit_cartesien(NumF1, Nom, marseille, NumF2, Piece, Quantite),[[f6, brown, marseille, f1, p1, 300], [f6, brown, marseille, f1, p2, 300], [f6, brown, marseille, f2, p2, 200], [f6, brown, marseille, f3, p3, 200], [f6, brown, marseille, f4, p1, 300], [f6, brown, marseille, f4, p2, 300], [f6, brown, marseille, f4, p4, 400], [f6, brown, marseille, f6, p5, 500], [f6, brown, marseille, f6, p6, 1000], [f6, brown, marseille, f6, p7, 300]])),
        assert_true(sortall([NumF1, Nom, lyon, NumF2, Piece, Quantite], produit_cartesien(NumF1, Nom, lyon, NumF2, Piece, Quantite),[[f1, dupont, lyon, f1, p1, 300], [f1, dupont, lyon, f1, p2, 300], [f1, dupont, lyon, f2, p2, 200], [f1, dupont, lyon, f3, p3, 200], [f1, dupont, lyon, f4, p1, 300], [f1, dupont, lyon, f4, p2, 300], [f1, dupont, lyon, f4, p4, 400], [f1, dupont, lyon, f6, p5, 500], [f1, dupont, lyon, f6, p6, 1000], [f1, dupont, lyon, f6, p7, 300]])),
        assert_true(sortall([NumF1, Nom, rennes, NumF2, Piece, Quantite], produit_cartesien(NumF1, Nom, rennes, NumF2, Piece, Quantite),[[f3, martin, rennes, f1, p1, 300], [f3, martin, rennes, f1, p2, 300], [f3, martin, rennes, f2, p2, 200], [f3, martin, rennes, f3, p3, 200], [f3, martin, rennes, f4, p1, 300], [f3, martin, rennes, f4, p2, 300], [f3, martin, rennes, f4, p4, 400], [f3, martin, rennes, f6, p5, 500], [f3, martin, rennes, f6, p6, 1000], [f3, martin, rennes, f6, p7, 300]])).

test_jointure :-
        assert_true(sortall([NumF, Nom, Ville, Piece, Quantite], jointure(NumF, Nom, Ville, Piece, Quantite),[[f1, dupont, lyon, p1, 300], [f1, dupont, lyon, p2, 300], [f2, durand, lille, p2, 200], [f3, martin, rennes, p3, 200], [f4, michel, clermontFerrand, p1, 300], [f4, michel, clermontFerrand, p2, 300], [f4, michel, clermontFerrand, p4, 400], [f6, brown, marseille, p5, 500], [f6, brown, marseille, p6, 1000], [f6, brown, marseille, p7, 300]])).

test_jointure_sup :-
        assert_true(sortall([NumF, Nom, Ville, Piece, Quantite], jointure_sup(NumF, Nom, Ville, Piece, Quantite),[[f4, michel, clermontFerrand, p4, 400], [f6, brown, marseille, p5, 500], [f6, brown, marseille, p6, 1000]])).

test_division :-
        assert_true(sortall(F, division(F),[f1, f4])).

test_total_pieces_livrees_fournisseur :-
        assert_true(sortall([NumF,QteLivree], total_pieces_livrees_fournisseur(NumF,QteLivree),[[f1, 600], [f2, 200], [f3, 200], [f4, 1000], [f5, 0], [f6, 1800]])).

test_est_compose_de :-
        assert_true(sortall(X, est_compose_de(voiture,X),[jante, moteur, piston, pneu, porte, roue, soupape, tole, vitre])),
        assert_true(sortall(X, est_compose_de(moteur,X),[piston, soupape])),
        assert_true(not est_compose_de(jante,X)).

test_nb_pieces_tot :-
        assert_true(sortall([Composant,Piece,Qte], nb_pieces_tot(Composant,Piece,Qte),[[moteur, piston, 4], [moteur, soupape, 16], [porte, tole, 1], [porte, vitre, 1], [roue, jante, 1], [roue, pneu, 1], [voiture, jante, 4], [voiture, piston, 4], [voiture, pneu, 4], [voiture, soupape, 16], [voiture, tole, 4], [voiture, vitre, 4]])).

test_nb_voiture :-
        assert_true(sortall(Nb, nb_voiture(Nb),[62])).




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
              
% =========================================================================================== 
% QUESTION 1 : Base de données
% =========================================================================================== 

assemblage(voiture, porte, 4).
assemblage(voiture, roue, 4).
assemblage(voiture, moteur, 1).
assemblage(roue, jante, 1).
assemblage(porte, tole, 1).
assemblage(porte, vitre, 1).
assemblage(roue, pneu, 1).
assemblage(moteur, piston, 4).
assemblage(moteur, soupape, 16).

           
piece(p1, tole, lyon).
piece(p2, jante, lyon).
piece(p3, jante, marseille).
piece(p4, pneu, clermontFerrand).
piece(p5, piston, toulouse).
piece(p6, soupape, lille).
piece(p7, vitre, nancy).
piece(p8, tole, marseille).
piece(p9, vitre, marseille).

                  
demandeFournisseur(dupont, lyon).
demandeFournisseur(michel, clermontFerrand).
demandeFournisseur(durand, lille).
demandeFournisseur(dupond, lille).
demandeFournisseur(martin, rennes).
demandeFournisseur(smith, paris).
demandeFournisseur(brown, marseille).
          
          
fournisseurReference(f1, dupont, lyon).
fournisseurReference(f2, durand, lille).
fournisseurReference(f3, martin, rennes).
fournisseurReference(f4, michel, clermontFerrand).
fournisseurReference(f5, smith, paris).
fournisseurReference(f6, brown, marseille).

                  
livraison(f1, p1, 300).
livraison(f2, p2, 200).
livraison(f3, p3, 200).
livraison(f4, p4, 400).
livraison(f6, p5, 500).
livraison(f6, p6, 1000).
livraison(f6, p7, 300).
livraison(f1, p2, 300).
livraison(f4, p2, 300).
livraison(f4, p1, 300).



% ============================================================================= 
% SECTION 2 : Opération relationnelles
% ============================================================================= 





% ============================================================================= 
% SECTION 3 : Au delà de l’algèbre relationnelle
% ============================================================================= 



% =============================================================================
% Tests complémentaires
% =============================================================================



