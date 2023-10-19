package insarennes.q5;

import java.util.ArrayList;
import java.util.List;

public class Velo {
    private List<Roue> roues;

    public Velo() {
        this.roues = new ArrayList<>();
    }

    public List<Roue> getRoues() {
        return roues;
    }

    public void addRoue(Roue roue) {
        this.roues.add(roue);
        roue.setVelo(this);
    }

    public void removeRoue(Roue roue) {
        this.roues.remove(roue);
        roue.setVelo(null);
    }
}
