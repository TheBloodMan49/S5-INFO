package insarennes.q2;

public class Guidon {
    private Velo velo;

    public Velo getVelo() {
        return this.velo;
    }

    public void setVelo(Velo v) {
        if (v == null && this.velo != null) {
            velo.setGuidon(null);
            this.velo = null;
        } else if (this.velo != v) {
            this.velo = v;
            v.setGuidon(this);
        }

    }
}
