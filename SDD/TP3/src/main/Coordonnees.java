public class Coordonnees {

    private final float x;
    private final float y;

    public Coordonnees(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String toString() {
        return "Coordonnées, x : " + this.x + ", y : " + this.y;
    }

    public boolean equals(Coordonnees cood) {
        return this.x == cood.x && this.y == cood.y;
    }
}
