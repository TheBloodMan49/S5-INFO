package insarennes.q2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class VeloTest {
	Guidon guidon;
	Velo velo;

	@BeforeEach
	void setUp() {
		guidon = new Guidon();
		velo = new Velo();
	}

	@Test
	void getSetGuidon() {
		velo.setGuidon(guidon);

		assertSame(guidon, velo.getGuidon());
		assertSame(velo, guidon.getVelo());
	}

	@Test
	void removeGuidon() {
		velo.setGuidon(guidon);
		velo.setGuidon(null);

		assertNull(guidon.getVelo());
		assertNull(velo.getGuidon());
	}

	@Test
	void setGuidonNull() {
		velo.setGuidon(null);

		assertNull(guidon.getVelo());
		assertNull(velo.getGuidon());
	}
}
