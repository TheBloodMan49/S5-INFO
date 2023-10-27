import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListeTabuleeTest {
    ListeTabulee<Integer> ldt;
    IterateurDeListeTabulee<Integer> ldtI;
    ListeTabulee<Integer> ldte;
    IterateurDeListeTabulee<Integer> ldteI;

    @BeforeEach
    public void setup() throws MyListOutOfBoundsException {
        ldt = new ListeTabulee<>();
        ldte = new ListeTabulee<>();
        ldtI = ldt.iterateur();
        ldteI = ldte.iterateur();
    }

    @Test
    public void testIsEmpty() throws MyListOutOfBoundsException, MyListEmptyException {
        assertTrue(ldt.estVide());
    }

    @Test
    public void testIsNotEmpty() throws MyListOutOfBoundsException{
        assertFalse(ldtI.ajouterD(25).liste.estVide());
    }

    @Test
    public void testAjouterThrows() throws MyListOutOfBoundsException, MyListEmptyException {
        for (int i = 0; i < 100; i++) {
            ldtI.ajouterD(25);
        }
        assertThrows(MyListOutOfBoundsException.class, () -> ldtI.ajouterD(25));
    }

    @Test
    public void testOterec() throws MyListOutOfBoundsException, MyListEmptyException {
        ldtI.ajouterD(25).ajouterD(26).pred().oterec();
        assertEquals(ldtI.valec(), 26);
    }

    @Test
    public void testOterecSecond() throws MyListOutOfBoundsException, MyListEmptyException {
        ldtI.ajouterD(20).ajouterD(30).pred().oterec();
        assertEquals(ldtI.entete().succ().valec(), 30);
    }

    @Test
    public void testSuccPred() throws MyListOutOfBoundsException, MyListEmptyException {
        ldtI.ajouterD(25).ajouterD(26).entete().succ().succ().pred();
        assertEquals(ldtI.valec(), 25);
    }

    @Test
    public void testPredSucc() throws MyListOutOfBoundsException, MyListEmptyException {
        ldtI.ajouterD(25).ajouterD(26).enqueue().pred().succ();
        assertEquals(ldtI.valec(), 26);
    }

    @Test
    public void isOutADroite() throws MyListOutOfBoundsException, MyListEmptyException {
        assertTrue(ldtI.enqueue().succ().estSorti());
    }

    @Test
    public void isOutAGauche() throws MyListOutOfBoundsException, MyListEmptyException {
        assertTrue(ldtI.entete().pred().estSorti());
    }

    @Test
    public void isOutNew() {
        assertTrue(new ListeTabulee<Integer>().iterateur().estSorti());
    }

    @Test
    public void isOutToTheLeft() throws MyListOutOfBoundsException{
        assertFalse(ldtI.ajouterD(250).entete().estSorti());
    }

    @Test
    public void isOutToTheRight() throws MyListOutOfBoundsException{
        assertFalse(ldtI.ajouterD(250).enqueue().estSorti());
    }

    @Test
    public void isOutSomewhere() throws MyListOutOfBoundsException, MyListEmptyException{
        assertFalse(ldtI.ajouterD(250).ajouterD(85).pred().estSorti());
    }

    @Test
    public void valecTest() throws MyListOutOfBoundsException, MyListEmptyException{
        assertEquals(ldtI.ajouterD(25).valec(), 25);
    }

    @Test
    public void valecSecondTest() throws MyListOutOfBoundsException, MyListEmptyException{
        assertEquals(ldtI.ajouterD(25).pred().valec(), 1);
    }

    @Test
    public void testSuccThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldteI.succ());
    }

    @Test
    public void testSuccThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldtI.succ().succ());
    }

    @Test
    public void testPredThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldteI.pred());
    }

    @Test
    public void testPredThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldtI.pred().pred());
    }

    @Test
    public void testOterecThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldteI.oterec());
    }

    @Test
    public void testOterecThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldtI.succ().oterec());
    }

    @Test
    public void testValecThrowsEmpty() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListEmptyException.class, () -> ldteI.valec());
    }

    @Test
    public void testValecThrowsOOB() throws MyListOutOfBoundsException, MyListEmptyException{
        assertThrows(MyListOutOfBoundsException.class, () -> ldtI.succ().valec());
    }
}
