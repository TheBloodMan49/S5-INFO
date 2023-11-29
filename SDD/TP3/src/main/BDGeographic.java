import Liste.ListeTabulee;

public class BDGeographic {
    private ListeTabulee<Enregistrement> bd;

    public BDGeographic() {
        this.bd = new ListeTabulee<>();
    }

    public boolean estPresent(Enregistrement e) {
        return e != null && this.bd.contains(e);
    }

    public void vider() {
        this.bd.clear();
    }

    public void ajouter(Enregistrement e) throws PresentException {
        if(estPresent(e)) throw new PresentException();
        this.bd.add(e);
    }

    public void retirer(Enregistrement e) throws AbsentException {
        if(!estPresent(e)) throw new AbsentException();
        this.bd.remove(e);
    }

    public String toString() {
        String res = "BDD :";
        for(Object e : this.bd){
            res += "\n[" + e.toString() + "]";
        }
        return res;
    }

    public Enregistrement ville(String v) {
        for(Enregistrement elem : this.bd) {
            if(elem.getNom().equals(v)) return elem;
        }
        return null;
    }

    public Enregistrement coord(Coordonnees c) {
        for (Enregistrement elem : this.bd) {
            if (elem.getCoordonnees().equals(c)) return elem;
        }
        return null;
    }

    public void retirerVille(String ville) throws AbsentException{
        Enregistrement enre = null;
        for (Enregistrement enregistrement:this.bd){
            if (enregistrement.getNom().equals(ville)) enre = enregistrement;
        }
        this.retirer(enre);
    }

    public void retirerCoord(Coordonnees coor) throws AbsentException{
        Enregistrement enre = null;
        for (Enregistrement enregistrement:this.bd){
            if (enregistrement.getCoordonnees().equals(coor)) enre = enregistrement;
        }
        this.retirer(enre);
    }

    public int population() {
        int pop = 0;
        for(Enregistrement elem : this.bd) pop += elem.getPop();
        return pop;
    }
}
