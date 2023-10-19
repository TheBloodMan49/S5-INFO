package insarennes.q2;

public class Velo {
    private Guidon guidon;

    public Guidon getGuidon() {
        return this.guidon;
    }

    public void setGuidon(Guidon g) {
        if (g == null && this.guidon != null) {
            guidon.setVelo(null);
        } else if (this.guidon != g) {
            this.guidon = g;
            g.setVelo(this);
        }

    }
}
