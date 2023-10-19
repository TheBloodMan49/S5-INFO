import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArbre {
    private Arbre chene;
    private Arbre pin;

    @BeforeEach
    public void setUp() {
        chene = new Chene(10, 100);
        pin = new Pin(5, 50);
    }

    @Test
    public void testAge() {
        assertEquals(10, chene.getAge());
        assertEquals(5, pin.getAge());
    }

    @Test
    public void testVolume() {
        assertEquals(100, chene.getVolume());
        assertEquals(50, pin.getVolume());
    }

    @Test
    public void testVieillir() {
        chene.vieillir();
        assertEquals(11, chene.getAge());
        pin.vieillir();
        assertEquals(6, pin.getAge());
    }

    @Test
    public void testPeutEtreCoupe() {
        assertEquals(true, chene.peutEtreCoupe());
        assertEquals(true, pin.peutEtreCoupe());
    }

    @Test
    public void testPeutPasEtreCoupe() {
        Arbre chene = new Chene(9, 100);
        assertEquals(false, chene.peutEtreCoupe());
        Arbre pin = new Pin(4, 50);
        assertEquals(false, pin.peutEtreCoupe());
    }

}
