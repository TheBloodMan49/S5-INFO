package insarennes.q4;

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
    }

    public void removeRoue(Roue roue) {
        this.roues.remove(roue);
    }
}
