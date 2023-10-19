public class Chene extends Arbre {
    public Chene(double age, double volume) {
        super(age, volume);
    }
    public int getPrix() {
        return 1000;
    }
    public double agePourCouper() {
        return 10;
    }
    public Fruit produireFruit() {
        return new Gland();
    }
}
