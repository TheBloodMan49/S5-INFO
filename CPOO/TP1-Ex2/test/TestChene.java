import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChene {
    @Test
    public void testPrix() {
        Chene chene = new Chene(10, 100);
        assertEquals(1000, chene.getPrix());
    }

    @Test
    public void testAgePourCouper() {
        Chene chene = new Chene(10, 100);
        assertEquals(10, chene.agePourCouper());
    }
}
