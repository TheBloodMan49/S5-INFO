public class Main {

    public static void main(String[] args) throws ValeurNonTrouveeException {
        ArbreBinaire arbreBinaire = getArbreBinaire("4");
        ArbreBinaire arbreX = getArbreBinaire("x");
        System.out.println("Affichage joli de l'arbre : ");
        arbreBinaire.dessiner();
        System.out.println("Affichage moche de l'arbre : ");
        System.out.println(arbreBinaire);
        System.out.println("Résultat de l'arbre : ");
        System.out.println(new ExprArith(arbreBinaire).evaluer());
        System.out.println("Résultat de l'arbreX : ");
        ExprArith x = new ExprArith(arbreX);
        x.simplifier();
        System.out.println(x);
        /*System.out.println("Affichage de la hauteur : ");
        System.out.println(arbreBinaire.hauteur());
        System.out.println("Remplace 5 par 4 : ");
        arbreBinaire.remplacer("5", "4");
        System.out.println(arbreBinaire);
        System.out.println("Combien de 4 ? : ");
        System.out.println(arbreBinaire.denombrer("4"));
        ExprArith exprArith = new ExprArith(arbreBinaire);
        System.out.println("L'expression de l'arbre est : ");
        System.out.println(exprArith);
        ArbreBinaire quatre = new ArbreBinaire("4");
        ExprArith quatreExpr = new ExprArith(quatre);
        System.out.println(quatreExpr);
        ArbreBinaire MegaPower = new ArbreBinaire("*");
        MegaPower.modifArbreG(arbreBinaire);
        MegaPower.modifArbreD(arbreBinaire);
        ExprArith megaExprQ = new ExprArith(MegaPower);
        System.out.println("L'expression du MegaTree est : ");
        System.out.println(megaExprQ);*/
    }

    private static ArbreBinaire getArbreBinaire(String entree) {
        ArbreBinaire arbreBinaire = new ArbreBinaire("+");
        ArbreBinaire arbreGauche1 = new ArbreBinaire("*");
        ArbreBinaire arbreGauche11 = new ArbreBinaire(entree);
        ArbreBinaire arbreGauche12 = new ArbreBinaire("pi");
        arbreGauche1.modifArbreG(arbreGauche11);
        arbreGauche1.modifArbreD(arbreGauche12);
        ArbreBinaire arbreDroite2 = new ArbreBinaire("/");
        ArbreBinaire arbreDroite22 = new ArbreBinaire("2");
        ArbreBinaire arbreDroite21 = new ArbreBinaire("5");
        arbreDroite2.modifArbreG(arbreDroite21);
        arbreDroite2.modifArbreD(arbreDroite22);
        arbreBinaire.modifArbreG(arbreGauche1);
        arbreBinaire.modifArbreD(arbreDroite2);
        return arbreBinaire;
    }

}
