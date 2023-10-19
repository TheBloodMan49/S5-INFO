package insarennes.q5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVelo {
    private Velo velo;
    private Roue roue;

    @BeforeEach
    public void setUp() {

        this.velo = new Velo();
        this.roue = new Roue();
    }

    @Test
    public void testGetRouesZero() {
        assertEquals(0, velo.getRoues().size());
    }

    @Test
    public void testGetRouesOne() {
        velo.addRoue(roue);
        assertEquals(1, velo.getRoues().size());
    }

    @Test
    public void testAddRoueSize() {
        velo.addRoue(roue);
        assertEquals(1, velo.getRoues().size());
    }

    @Test
    public void testAddRoueElem() {
        velo.addRoue(roue);
        assertEquals(roue, velo.getRoues().get(0));
    }

    @Test
    public void testRemoveRoueZero() {
        velo.removeRoue(roue);
        assertEquals(0, velo.getRoues().size());
    }

    @Test
    public void testRemoveRoueSize() {
        velo.addRoue(roue);
        velo.removeRoue(roue);
        assertEquals(0, velo.getRoues().size());
    }

    @Test
    public void testRemoveRoueElem() {
        velo.addRoue(roue);
        velo.removeRoue(roue);
        assertEquals(false, velo.getRoues().contains(roue));
    }

    @Test
    public void testRemoveRoueCompisition() {
        velo.addRoue(roue);
        velo.removeRoue(roue);
        assertEquals(null, roue.getVelo());
    }
}
