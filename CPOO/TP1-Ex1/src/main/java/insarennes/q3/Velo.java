package insarennes.q3;

public class Velo {
    private Guidon guidon;

    public Velo() {
        this.guidon = null;
    }

    public Velo(Guidon g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }
        this.guidon = g;
    }

    public Guidon getGuidon() {
        return this.guidon;
    }

    public void setGuidon(Guidon g) {
        if (g != null) this.guidon = g;
    }
}
