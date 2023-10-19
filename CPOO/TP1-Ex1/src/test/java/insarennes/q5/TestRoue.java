package insarennes.q5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoue {
    private Roue roue;
    private Velo velo;

    @BeforeEach
    public void setUp() {
        this.roue = new Roue();
        this.velo = new Velo();
    }

    @Test
    public void testGetVeloNull() {
        assertEquals(null, this.roue.getVelo());
    }

    @Test
    public void testGetVeloNotNull() {
        this.roue.setVelo(this.velo);
        assertEquals(this.velo, this.roue.getVelo());
    }
}
