import java.util.HashMap;
import java.util.Objects;

public class Horaire {

    private String jour;
    private int heure;

    public Horaire(String jour, int heure) {
        this.jour = jour;
        this.heure = heure;
    }

    public int getHeure() {
        return this.heure;
    }

    public String getJour() {
        return this.jour;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Horaire && this.heure == ((Horaire) o).getHeure() && this.jour.equalsIgnoreCase(this.getJour()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, heure);
    }
}
