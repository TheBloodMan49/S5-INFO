import java.util.LinkedList;

public class TableCouples {

    private LinkedList<Couple>[] listes;

    public TableCouples() {
        this.listes = new LinkedList[10000];
        for (int i = 0; i < listes.length; i++){
            this.listes[i] = new LinkedList<>();
        }
    }

    @Override
    public String toString() {
        StringBuilder rtn = new StringBuilder("Ceci est une table de traduction\n");
        for (LinkedList<Couple> liste : this.listes) {
            for (Couple c : liste) {
                rtn.append(c).append("\n");
            }
        }
        return rtn.toString();
    }

    public boolean ajouter(Mot fr, Mot en) {
        this.listes[fr.hashCode()].add(new Couple(fr,en));
        return true;
    }

    public Mot traduire(Mot mot) {
        for (Couple c : this.listes[mot.hashCode()]) {
            if (c.getMot().equals(mot)) return c.compCoupleMot(mot);
        }
        return null;
    }
}
