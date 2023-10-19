import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForet {
    private Foret foret;
    private ArrayList<Arbre> list;
    @BeforeEach
    public void setUp() {
        list = new ArrayList<Arbre>();
        list.add(new Chene(10, 100));
        foret = new Foret(list);

    }

    @Test
    public void testGetArbres() {
        assertEquals(list, foret.getArbres());
    }

    @Test
    public void testGetArbresCoupe() {
        assertEquals(new ArrayList<Arbre>(), foret.getArbresCoupe());
    }

    @Test
    public void testPlanterArbre() {
        foret.planterArbre(new Pin(5, 50));
        assertEquals(2, foret.getArbres().size());
    }

    @Test
    public void testGetPrixTotal() {
        foret.planterArbre(new Pin(4, 50));
        assertEquals(1000, foret.getPrixTotal());
    }

    @Test
    public void testCouperArbre() {
        foret.couperArbre();
        assertEquals(0, foret.getArbres().size());
        assertEquals(1, foret.getArbresCoupe().size());
    }

    @Test
    public void testGetNombreChene() {
        foret.planterArbre(new Pin(4, 50));
        assertEquals(1, foret.getNombreChene());
    }
}
