package insarennes.q2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GuidonTest {
	Guidon guidon;
	Velo velo;

	@BeforeEach
	void setUp() {
		guidon = new Guidon();
		velo = new Velo();
	}

	@Test
	void getSetVelo() {
		guidon.setVelo(velo);

		assertSame(velo, guidon.getVelo());
		assertSame(guidon, velo.getGuidon());
	}

	@Test
	void removeVelo() {
		guidon.setVelo(velo);
		guidon.setVelo(null);

		assertNull(guidon.getVelo());
		assertNull(velo.getGuidon());
	}

	@Test
	void setVeloNull() {
		guidon.setVelo(null);

		assertNull(guidon.getVelo());
		assertNull(velo.getGuidon());
	}
}
