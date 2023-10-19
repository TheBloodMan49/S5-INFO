package insarennes.q3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VeloTest {
	@Test
	void constructorNull() {
		assertThrows(IllegalArgumentException.class, () -> new Velo(null));
	}

	@Nested
	class WithGuidon {
		Guidon guidon;
		Velo velo;

		@BeforeEach
		void setUp() {
			guidon = new Guidon();
			velo = new Velo(guidon);
		}

		@Test
		void getGuidon() {
			assertSame(guidon, velo.getGuidon());
		}

		@Test
		void setGuidonOK() {
			final var guidon2 = new Guidon();
			velo.setGuidon(guidon2);
			assertSame(guidon2, velo.getGuidon());
		}

		@Test
		void setGuidonNull() {
			velo.setGuidon(null);
			assertSame(guidon, velo.getGuidon());
		}
	}
}
