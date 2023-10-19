public abstract class Arbre {
    private double age;
    private double volume;

    public abstract int getPrix();
    public abstract double agePourCouper();
    public Arbre(double age, double volume) {
        this.age = age;
        this.volume = volume;
    }
    public double getAge() {
        return age;
    }
    public double getVolume() {
        return volume;
    }
    public void vieillir() {
        this.age++;
    }

    public boolean peutEtreCoupe() {
        return this.age >= this.agePourCouper();
    }
    public abstract Fruit produireFruit();
}