import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListeDoubleChainageTest {
    ListeDoubleChainage<Integer> ldc;
    ListeDoubleChainage<Integer> ldce;

    @BeforeEach
    public void setup() throws MyListOutOfBoundsException {
        ldc = new ListeDoubleChainage<>();
        ldce = new ListeDoubleChainage<>();
        ldc.ajouterD(1);
    }

    @Test
    public void testIsEmpty() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.oterec();
        assertTrue(ldc.estVide());
    }

    @Test
    public void testIsNotEmpty() throws MyListOutOfBoundsException{
        assertFalse(ldc.ajouterD(25).estVide());
    }

    @Test
    public void testAjouterThrows() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.ajouterD(25).succ();
        assertThrows(MyListOutOfBoundsException.class, () -> ldc.ajouterD(25));
    }

    @Test
    public void testOterec() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.ajouterD(25).ajouterD(26).pred().oterec();
        assertEquals(ldc.valec(), 26);
    }

    @Test
    public void testOterecSecond() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.ajouterD(20).ajouterD(30).pred().oterec();
        assertEquals(ldc.entete().succ().valec(), 30);
    }

    @Test
    public void testSuccPred() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.ajouterD(25).ajouterD(26).entete().succ().succ().pred();
        assertEquals(ldc.valec(), 25);
    }

    @Test
    public void testPredSucc() throws MyListOutOfBoundsException, MyListEmptyException {
        ldc.ajouterD(25).ajouterD(26).enqueue().pred().succ();
        assertEquals(ldc.valec(), 26);
    }

    @Test
    public void isOutADroite() throws MyListOutOfBoundsException, MyListEmptyException {
        assertTrue(ldc.enqueue().succ().estSorti());
    }

    @Test
    public void isOutAGauche() throws MyListOutOfBoundsException, MyListEmptyException {
        assertTrue(ldc.entete().pred().estSorti());
    }

    @Test
    public void isOutNew() {
        assertTrue(new ListeDoubleChainage<Integer>().estSorti());
    }

    @Test
    public void isOutToTheLeft() throws MyListOutOfBoundsException{
        assertFalse(ldc.ajouterD(250).entete().estSorti());
    }

    @Test
    public void isOutToTheRight() throws MyListOutOfBoundsException{
        assertFalse(ldc.ajouterD(250).enqueue().estSorti());
    }

    @Test
    public void isOutSomewhere() throws MyListOutOfBoundsException, MyListEmptyException{
        assertFalse(ldc.ajouterD(250).ajouterD(85).pred().estSorti());
    }

    @Test
    public void valecTest() throws MyListOutOfBoundsException, MyListEmptyException{
        assertEquals(ldc.ajouterD(25).valec(), 25);
    }

    @Test
    public void valecSecondTest() throws MyListOutOfBoundsException, MyListEmptyException{
        assertEquals(ldc.ajouterD(25).pred().valec(), 1);
    }

    @Test
    public void testSuccThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldce.succ());
    }

    @Test
    public void testSuccThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldc.succ().succ());
    }

    @Test
    public void testPredThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldce.pred());
    }

    @Test
    public void testPredThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldc.pred().pred());
    }

    @Test
    public void testOterecThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldce.oterec());
    }

    @Test
    public void testOterecThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldc.succ().oterec());
    }

    @Test
    public void testValecThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldce.valec());
    }

    @Test
    public void testValecThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldc.succ().valec());
    }
}
