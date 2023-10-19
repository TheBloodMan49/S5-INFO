import java.util.ArrayList;
import java.util.List;

public class Foret {
    private List<Arbre> arbres;
    private List<Arbre> arbresCoupe;

    public Foret(List<Arbre> arbres, List<Arbre> arbresCoupe) {
        this.arbres = arbres;
        this.arbresCoupe = arbresCoupe;
    }
    public Foret(List<Arbre> arbres) {
        this.arbres = arbres;
        this.arbresCoupe = new ArrayList<Arbre>();
    }
    public Foret() {
        this.arbres = new ArrayList<Arbre>();
        this.arbresCoupe = new ArrayList<Arbre>();
    }
    public List<Arbre> getArbres() {
        return arbres;
    }
    public List<Arbre> getArbresCoupe() {
        return arbresCoupe;
    }
    public void planterArbre(Arbre arbre) {
        this.arbres.add(arbre);
    }
    public int getPrixTotal() {
        int prixTotal = 0;
        for (Arbre arbre : this.arbres) {
            if (arbre.peutEtreCoupe()) prixTotal += arbre.getPrix();
        }
        return prixTotal;
    }
    public void couperArbre() {
        for (Arbre arbre : this.arbres) {
            if (arbre.peutEtreCoupe()) {
                this.arbresCoupe.add(arbre);
                this.arbres.remove(arbre);
                break;
            }
        }
    }
    public int getNombreChene() {
        int nombreChene = 0;
        for (Arbre arbre : this.arbres) {
            if (arbre instanceof Chene) nombreChene++;
        }
        return nombreChene;
    }
}
