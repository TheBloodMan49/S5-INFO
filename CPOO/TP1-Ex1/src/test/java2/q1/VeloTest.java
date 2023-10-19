package insarennes.q1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class VeloTest {
	@Test
	void getSetGuidon() {
		var velo = new Velo();
		var guidon = new Guidon();
		velo.setGuidon(guidon);

		assertSame(guidon, velo.getGuidon());
	}
}
