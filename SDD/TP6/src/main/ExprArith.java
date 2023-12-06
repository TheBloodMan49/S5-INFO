import java.util.HashMap;

public class ExprArith {

    protected Arbre arbre;
    private static final HashMap<String, Double> symboles;

    static {
        symboles = new HashMap<>();
        symboles.put("pi", Math.PI);
        symboles.put("e", Math.E);
    }

    public ExprArith(Arbre a) {
        this.arbre = a;
    }

    public String toString() {
        if (this.arbre.arbreG().estVide()) return this.arbre.racine();
        ExprArith expGauche = new ExprArith(this.arbre.arbreG()),
                expDroite = new ExprArith(this.arbre.arbreD());
        return "(" + expGauche.toString() + " " + this.arbre.racine() + " " + expDroite.toString() + ")";
    }

    public void associerValeur(String symbole, double valeur) {
        symboles.put(symbole, valeur);
    }

    public double evaluer() throws ValeurNonTrouveeException, ClassCastException {
        if (arbre.arbreG().estVide()) return (double) valeur(arbre.racine());
        double valeurGauche = new ExprArith(this.arbre.arbreG()).evaluer(),
            valeurDroite = new ExprArith(this.arbre.arbreD()).evaluer();
        switch(arbre.racine()) {
            case "+" -> {
                return valeurGauche + valeurDroite;
            }
            case "-" -> {
                return valeurGauche - valeurDroite;
            }
            case "*" -> {
                return valeurGauche * valeurDroite;
            }
            case "/" -> {
                return valeurGauche / valeurDroite;
            }
            default -> throw new ValeurNonTrouveeException();
        }
    }

    public Object valeur(String s) {
        if (symboles.containsKey(s.toLowerCase())) {
            return symboles.get(s.toLowerCase());
        } else {
            try {
                return Double.parseDouble(s);
            } catch (Exception e) {
                return s;
            }
        }
    }

    public void simplifier() {
        if (!arbre.arbreG().estVide()) new ExprArith(this.arbre.arbreG()).simplifier();
        if (!arbre.arbreD().estVide()) new ExprArith(this.arbre.arbreD()).simplifier();

        try {
            double res = this.evaluer();
            this.arbre.vider();
            this.arbre.modifRacine("" + res);
        } catch (ValeurNonTrouveeException | ClassCastException ignored) {}
    }
}
