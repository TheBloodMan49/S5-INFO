import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPin {
    @Test
    public void testPrix() {
        Pin pin = new Pin(5, 50);
        assertEquals(500, pin.getPrix());
    }
    @Test
    public void testAgePourCouper() {
        Pin pin = new Pin(5, 50);
        assertEquals(5, pin.agePourCouper());
    }
}
