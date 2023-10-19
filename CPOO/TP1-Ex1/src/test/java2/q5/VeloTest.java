package insarennes.q5;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VeloTest {
	Velo velo;

	@BeforeEach
	void setUp() {
		velo = new Velo();
	}

	@Test
	void getRoues() {
		assertNotNull(velo.getRoues());
		assertTrue(velo.getRoues().isEmpty());
	}

	@Test
	void removeNull() {
		velo.removeRoue(null);
		assertTrue(velo.getRoues().isEmpty());
	}

	@Nested
	class WithRoues {
		Roue roue1;
		Roue roue2;

		@BeforeEach
		void setUp() {
			roue1 = new Roue();
			roue2 = new Roue();
			velo.addRoue(roue1);
			velo.addRoue(roue2);
		}

		@Test
		void addRoue() {
			assertEquals(List.of(roue1, roue2), velo.getRoues());
			assertSame(velo, roue1.getVelo());
			assertSame(velo, roue2.getVelo());
		}

		@Test
		void removeRoue1() {
			velo.removeRoue(roue1);
			assertEquals(List.of(roue2), velo.getRoues());
			assertNull(roue1.getVelo());
			assertSame(velo, roue2.getVelo());
		}

		@Test
		void removeRoue2() {
			velo.removeRoue(roue2);
			assertEquals(List.of(roue1), velo.getRoues());
			assertSame(velo, roue1.getVelo());
			assertNull(roue2.getVelo());
		}

		@Test
		void removeNull() {
			velo.removeRoue(null);
			assertEquals(List.of(roue1, roue2), velo.getRoues());
		}
	}
}
