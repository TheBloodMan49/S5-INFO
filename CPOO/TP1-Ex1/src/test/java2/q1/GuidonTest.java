package insarennes.q1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class GuidonTest {
	@Test
	void getSetVelo() {
		var guidon = new Guidon();
		var velo = new Velo();
		guidon.setVelo(velo);

		assertSame(velo, guidon.getVelo());
	}
}
