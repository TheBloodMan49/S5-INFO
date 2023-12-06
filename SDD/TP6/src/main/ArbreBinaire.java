public class ArbreBinaire implements Arbre {

    private String valeur;
    private Arbre arbreG;
    private Arbre arbreD;

    public ArbreBinaire(String valeur, ArbreBinaire arbreG, ArbreBinaire arbreD) {
        this.valeur = valeur;
        this.arbreG = arbreG;
        this.arbreD = arbreD;
    }

    public ArbreBinaire(String valeur) {
        this(valeur, new ArbreBinaire(), new ArbreBinaire());
    }

    public ArbreBinaire() {
        this("", null, null);
    }

    @Override
    public String racine() {
        return this.valeur;
    }

    @Override
    public Arbre arbreG() {
        return this.arbreG;
    }

    @Override
    public Arbre arbreD() {
        return this.arbreD;
    }

    @Override
    public boolean estVide() {
        return this.valeur.isEmpty() && this.arbreG == null && this.arbreD == null;
    }

    @Override
    public void vider() {
        this.valeur = "";
        this.arbreG = new ArbreBinaire();
        this.arbreD = new ArbreBinaire();
    }

    @Override
    public void modifRacine(Object r) {
        this.valeur = (String) r;
    }

    @Override
    public void modifArbreG(Arbre a) {
        this.arbreG = a;
    }

    @Override
    public void modifArbreD(Arbre a) {
        this.arbreD = a;
    }

    @Override
    public String toString() {
        String res = "";

        if (!this.arbreG.estVide())
            res += this.arbreG.toString() + " ";

        res += this.valeur;

        if (!this.arbreD.estVide())
            res += " " + this.arbreD.toString();
        return res.toString();
    }

    @Override
    public void dessiner() {
        Trees.draw(this);
    }

    @Override
    public int hauteur() throws LArbreEstVideException {
        if (this.estVide()) throw new LArbreEstVideException();
        int gauche = 0, droite = 0;
        if (!this.arbreG.estVide()) gauche = this.arbreG.hauteur();
        if (!this.arbreD.estVide()) droite = this.arbreD.hauteur();
        return 1 + Math.max(gauche, droite);
    }

    @Override
    public int denombrer(String n) {
        if (this.estVide()) return 0;
        int res = this.valeur.equals(n) ? 1 : 0;
        if (this.arbreG != null) res += this.arbreG.denombrer(n);
        if (this.arbreD != null) res += this.arbreD.denombrer(n);
        return res;
    }

    @Override
    public void remplacer(String n1, String n2) {
        if (this.valeur.equals(n1)) this.valeur = n2;
        if (!this.arbreG.estVide()) this.arbreG.remplacer(n1, n2);
        if (!this.arbreD.estVide()) this.arbreD.remplacer(n1, n2);
    }
}
