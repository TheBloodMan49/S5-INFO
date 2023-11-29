public class Enregistrement {

    private final String nom;
    private final Coordonnees coordonnees;
    private final int pop;

    public Enregistrement(String nom, Coordonnees coordonnees, int pop) {
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.pop = pop;
    }

    public String getNom() {
        return nom;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public int getPop() {
        return pop;
    }

    public String toString() {
        return "Ville :\nNom : " + this.nom + "\n" + this.coordonnees.toString() + "\nPopulation : " + this.pop;
    }

    public boolean equals(Object o) {
        if(!(o instanceof Enregistrement e)) return false;
        return this.nom.equals(e.nom) &&
                this.coordonnees.equals(e.coordonnees) &&
                this.pop == e.pop;
    }
}
