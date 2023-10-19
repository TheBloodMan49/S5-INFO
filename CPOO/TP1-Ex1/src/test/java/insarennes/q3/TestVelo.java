package insarennes.q3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVelo {
    private Guidon guidon;
    private Velo velo;

    @BeforeEach
    public void setUp() {
        this.guidon = new Guidon();
        this.velo = new Velo();
    }

    @Test
    public void testGetGuidon() {
        velo.setGuidon(guidon);
        assertEquals(guidon, velo.getGuidon());
    }

    @Test
    public void testGuidonNoAccessToVelo() {
        assertEquals(null, guidon.getVelo());
    }
}
