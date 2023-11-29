import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmploiDuTempsTest {

    private EmploiDuTemps emploiDuTemps;
    private List<String> enseignants;

    @BeforeEach
    public void setup() {
        enseignants = new ArrayList<>(Arrays.asList("David", "John", "Albert"));
        emploiDuTemps = new EmploiDuTemps(enseignants);
    }

    @Test
    public void ajoutFailTest() {
        emploiDuTemps.ajoutCours("David", "lundi", 10, "Algebra");
        assertThrows(RuntimeException.class, () -> emploiDuTemps.ajoutCours("David", "lundi", 10, "MSDB"));
    }

    @Test
    public void accessCoursTest() {
        emploiDuTemps.ajoutCours("David", "lundi", 10, "Algebra");
        assertTrue(emploiDuTemps.getCours("David").containsKey(new Horaire("lundi", 10)));
    }

    @Test
    public void accessCoursFailTest() {
        assertFalse(emploiDuTemps.getCours("David").containsKey(new Horaire("lundi", 10)));
    }

    @Test
    public void mondayTenTest() {
        emploiDuTemps.ajoutCours("David", "lundi", 10, "Algebra");
        emploiDuTemps.ajoutCours("John", "lundi", 10, "Rust");
        emploiDuTemps.ajoutCours("Albert", "lundi", 8, "FFXIV");
        assertEquals(2, emploiDuTemps.mondayTen());
    }

    @Test
    public void mondayTest() {
        emploiDuTemps.ajoutCours("David", "mercredi", 10, "Algebra");
        emploiDuTemps.ajoutCours("John", "mardi", 10, "Rust");
        emploiDuTemps.ajoutCours("John", "lundi", 16, "OcAmL");
        emploiDuTemps.ajoutCours("Albert", "lundi", 8, "FFXIV");
        assertEquals(2, emploiDuTemps.monday());
    }

    @Test
    public void earlyBirdTest() {
        emploiDuTemps.ajoutCours("John", "lundi", 10, "Rust");
        emploiDuTemps.ajoutCours("John", "mardi", 16, "OcAmL");
        emploiDuTemps.ajoutCours("David", "mercredi", 14, "Algebra");
        emploiDuTemps.ajoutCours("Albert", "lundi", 8, "FFXIV");
        emploiDuTemps.ajoutCours("Albert", "vendredi", 10, "CS2");
        assertEquals("Albert", emploiDuTemps.earlyBird());
    }

    @Test
    public void championTest() {
        emploiDuTemps.ajoutCours("John", "lundi", 10, "Rust");
        emploiDuTemps.ajoutCours("John", "mardi", 16, "Rust");
        emploiDuTemps.ajoutCours("David", "mercredi", 14, "Algebra");
        emploiDuTemps.ajoutCours("Albert", "lundi", 8, "FFXIV");
        emploiDuTemps.ajoutCours("Albert", "vendredi", 10, "CS2");
        assertEquals("Albert", emploiDuTemps.champion());
    }

    @Test
    public void minimalRoomTest() {
        emploiDuTemps.ajoutCours("John", "lundi", 10, "Rust");
        emploiDuTemps.ajoutCours("John", "lundi", 12, "Rust");
        emploiDuTemps.ajoutCours("David", "lundi", 10, "Algebra");
        emploiDuTemps.ajoutCours("Albert", "lundi", 8, "FFXIV");
        emploiDuTemps.ajoutCours("Albert", "lundi", 10, "CS2");
        assertEquals(3, emploiDuTemps.minimalRooms());
    }

}
