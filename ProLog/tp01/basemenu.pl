hors_d_oeuvre(artichauts_Melanie).
hors_d_oeuvre(truffes_sous_le_sel).
hors_d_oeuvre(cresson_oeuf_poche).

viande(grillade_de_boeuf).
viande(poulet_au_tilleul).

poisson(bar_aux_algues).
poisson(saumon_oseille).

dessert(sorbet_aux_poires).
dessert(fraises_chantilly).
dessert(melon_en_surprise).

calories(artichauts_Melanie, 150).
calories(truffes_sous_le_sel, 202).
calories(cresson_oeuf_poche, 212).
calories(grillade_de_boeuf, 532).
calories(poulet_au_tilleul, 400).
calories(bar_aux_algues, 292).
calories(saumon_oseille, 254).
calories(sorbet_aux_poires, 223).
calories(fraises_chantilly, 289).
calories(melon_en_surprise, 122).


/* Q1.1
*  dessert(X).
*  viande(X). */

/* Q2.2 */
plat(X) :- poisson(X).
plat(X) :- viande(X).
/* plat(saumon_oseille).
*  plat(X). */

repas(H,P,D) :-
  hors_d_oeuvre(H),
  plat(P),
  dessert(D).
/* repas(artichauts_Melanie,bar_aux_algues,X). */

plat200_400(X) :- plat(X), calories(X,Y), Y >= 200, Y =< 400.
/* plat200_400(bar_aux_algues). */

plat_bar(X) :- plat(X), calories(X,Y), calories(bar_aux_algues,Z), Y > Z.
/* plat_bar(grillade_de_boeuf). */

val_cal(H, P, D, C) :- repas(H,P,D), calories(H,X), calories(P,Y), calories(D,Z), 
  C is X+Y+Z.
/* val_cal(artichauts_Melanie,grillade_de_boeuf,fraises_chantilly,X). */

repas_eq(H,P,D) :- val_cal(H,P,D,C), C < 800.
/* repas_eq(artichauts_Melanie,bar_aux_algues,fraises_chantilly)
