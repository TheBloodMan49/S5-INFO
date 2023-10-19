package insarennes.q4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVelo {
    private Velo velo;

    @BeforeEach
    public void setUp() {

        this.velo = new Velo();
    }

    @Test
    public void testGetRouesZero() {
        assertEquals(0, this.velo.getRoues().size());
    }

    @Test
    public void testGetRouesOne() {
        Roue roue = new Roue();
        this.velo.addRoue(roue);
        assertEquals(1, this.velo.getRoues().size());
    }

    @Test
    public void testAddRoueSize() {
        Roue roue = new Roue();
        this.velo.addRoue(roue);
        assertEquals(1, this.velo.getRoues().size());
    }

    @Test
    public void testAddRoueElem() {
        Roue roue = new Roue();
        this.velo.addRoue(roue);
        assertEquals(roue, this.velo.getRoues().get(0));
    }

    @Test
    public void testRemoveRoueZero() {
        Roue roue = new Roue();
        this.velo.removeRoue(roue);
        assertEquals(0, this.velo.getRoues().size());
    }

    @Test
    public void testRemoveRoueSize() {
        Roue roue = new Roue();
        this.velo.addRoue(roue);
        this.velo.removeRoue(roue);
        assertEquals(0, this.velo.getRoues().size());
    }

    @Test
    public void testRemoveRoueElem() {
        Roue roue = new Roue();
        this.velo.addRoue(roue);
        this.velo.removeRoue(roue);
        assertEquals(false, this.velo.getRoues().contains(roue));
    }
}
