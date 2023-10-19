package insarennes.q1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVelo {
    private Guidon guidon;

    @BeforeEach
    public void setUp() {
        this.guidon = new Guidon();
    }

    @Test
    public void testGetGuidon() {
        Velo v = new Velo();
        v.setGuidon(this.guidon);
        assertEquals(this.guidon, v.getGuidon());
    }
}
