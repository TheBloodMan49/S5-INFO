package insarennes.q1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGuidon {
    private Velo velo;

    @BeforeEach
    public void setUp() {
        this.velo = new Velo();
    }

    @Test
    public void testGetVelo() {
        Guidon g = new Guidon();
        g.setVelo(this.velo);
        assertEquals(this.velo, g.getVelo());
    }

}
