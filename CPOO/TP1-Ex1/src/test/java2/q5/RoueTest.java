package insarennes.q5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoueTest {
	Roue roue;
	Velo velo;

	@BeforeEach
	void setUp() {
		roue = new Roue();
		velo = new Velo();
	}

	@Test
	void getVelo() {
		assertNull(roue.getVelo());
	}

	@Test
	void setVeloNullInit() {
		roue.setVelo(null);
		assertNull(roue.getVelo());
	}

	@Test
	void setVelo() {
		roue.setVelo(velo);
		assertSame(velo, roue.getVelo());
		assertTrue(velo.getRoues().contains(roue));
	}

	@Test
	void setNewVelo() {
		final var velo2 = new Velo();
		roue.setVelo(velo);
		roue.setVelo(velo2);
		assertSame(velo2, roue.getVelo());
		assertTrue(velo2.getRoues().contains(roue));
		assertFalse(velo.getRoues().contains(roue));
	}
}
