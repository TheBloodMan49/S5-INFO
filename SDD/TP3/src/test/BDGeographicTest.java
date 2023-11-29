import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BDGeographicTest {

    public BDGeographic bdGeographic;
    Coordonnees cood1;
    Coordonnees cood2;
    Enregistrement ville1;
    Enregistrement ville2;

    @BeforeEach
    public void setup() {
        bdGeographic = new BDGeographic();
        cood1 = new Coordonnees(3.14f, 9.81f);
        cood2 = new Coordonnees(3.14f, 7.39f);
        ville1 = new Enregistrement("Paris", cood1, 25000);
        ville2 = new Enregistrement("Paris", cood2, 25000);
    }

    @Test
    public void estPresentTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        assertFalse(bdGeographic.estPresent(ville2));
        bdGeographic.ajouter(ville2);
        assertTrue(bdGeographic.estPresent(ville2));
    }

    @Test
    public void viderTest() throws PresentException {
        bdGeographic.ajouter(ville2);
        bdGeographic.ajouter(ville1);
        bdGeographic.vider();
        assertFalse(bdGeographic.estPresent(ville2));
        assertFalse(bdGeographic.estPresent(ville1));
    }

    @Test
    public void ajouterExceptionTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        assertThrows(PresentException.class, () -> bdGeographic.ajouter(ville1));
    }

    @Test
    public void retirerExceptionTest() {
        assertThrows(AbsentException.class, () -> bdGeographic.retirer(ville1));
    }

    @Test
    public void toStringTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        String toString = """
                BDD :
                [Ville :
                Nom : Paris
                Coordonnées, x : 3.14, y : 9.81
                Population : 25000]""";
        assertEquals(toString, bdGeographic.toString());
    }

    @Test
    public void villePresentTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        assertEquals(ville1, bdGeographic.ville("Paris"));
    }

    @Test
    public void villeAbsentTest() {
        assertNull(bdGeographic.ville("Paris"));
    }

    @Test
    public void coordPresentTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        assertEquals(ville1, bdGeographic.coord(cood1));
    }

    @Test
    public void coodAbsentTest() {
        assertNull(bdGeographic.coord(cood1));
    }

    @Test
    public void retirerVillePresentTest() throws PresentException, AbsentException {
        bdGeographic.ajouter(ville1);
        bdGeographic.retirerVille("Paris");
        assertFalse(bdGeographic.estPresent(ville1));
    }

    @Test
    public void retirerVilleAbsentTest() {
        assertThrows(AbsentException.class, () -> bdGeographic.retirerVille("Paris"));
    }

    @Test
    public void retirerCoodPresentTest() throws PresentException, AbsentException {
        bdGeographic.ajouter(ville1);
        bdGeographic.retirerCoord(cood1);
        assertFalse(bdGeographic.estPresent(ville1));
    }

    @Test
    public void retirerCoodAbsentTest() {
        assertThrows(AbsentException.class, () -> bdGeographic.retirerCoord(cood2));
    }

    @Test
    public void populationSommeTest() throws PresentException {
        bdGeographic.ajouter(ville1);
        bdGeographic.ajouter(ville2);
        assertEquals(50000, bdGeographic.population());
    }

    @Test
    public void populationVideTest() {
        assertEquals(0, bdGeographic.population());
    }

}
