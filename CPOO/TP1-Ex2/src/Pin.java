public class Pin extends Arbre {
    public Pin(double age, double volume) {
        super(age, volume);
    }
    public int getPrix() {
        return 500;
    }
    public double agePourCouper() {
        return 5;
    }
    public Fruit produireFruit() {
        return new Cone();
    }
}
