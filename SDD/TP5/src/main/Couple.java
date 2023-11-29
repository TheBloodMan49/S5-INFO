public class Couple {

    private final Mot mot;
    private final Mot traduction;

    public Couple(Mot mot, Mot traduction) {
        this.mot = mot;
        this.traduction = traduction;
    }

    @Override
    public String toString() {
        return "FR : " + mot + ", EN : " + traduction;
    }

    public Mot compCoupleMot(Mot mot)  {
        if (mot.equals(this.mot)) {
            return this.traduction;
        }
        return null;
    }

    public Mot getMot() {
        return mot;
    }
}
