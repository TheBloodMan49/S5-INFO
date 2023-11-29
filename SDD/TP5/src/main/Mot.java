import java.util.Objects;

public class Mot {

    private final String mot;

    public Mot(String mot) {
        this.mot = mot.toLowerCase();
    }

    @Override
    public String toString() {
        return this.mot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mot mot1 = (Mot) o;
        return Objects.equals(mot, mot1.mot);
    }

    @Override
    public int hashCode() {
        return this.mot.charAt(0) * 26 + this.mot.charAt(1);
    }
}
