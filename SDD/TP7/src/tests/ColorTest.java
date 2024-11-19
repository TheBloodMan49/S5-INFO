import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * ColorTest
 * Class implementing the tests for the Color class.
 */
public class ColorTest {

	/**
	 * Test method for {@link Color#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		assertEquals(new Color(255, 255, 255, 255), new Color(255, 255, 255, 255));
	}
	
	/**
	 * Test method for {@link Color#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("(127, 127, 127,   0)", new Color(127, 127, 127, 0).toString());
	}
	
	/**
	 * Test method for {@link Color#distance(Color)}.
	 */
	@Test
	public void testDistance() {
		Color c1, c2, c3;

		c1 = new Color(255, 255, 255, 0);
		c2 = new Color(127, 127, 127, 0);
		c3 = new Color(128, 128, 128, 0);

		assertEquals(c1.distance(c2), c3);
		assertEquals(c2.distance(c1), c3);
	}

	/**
	 * Test method for {@link Color#average(Color, double)}.
	 */
	@Test
	public void testAverage() {
		Color c1, c2, c3, c4;
		
		c1 = new Color(255, 255, 255, 1);
		c2 = new Color(1, 1, 1, 255);
		c3 = new Color(128, 128, 128, 128);
		c4 = new Color(64, 64, 64, 191);
		
		assertEquals(c1.average(c2, 0.5), c3);
		assertEquals(c2.average(c1, 0.5), c3);
		assertEquals(c1.average(c2, 0.25), c4);
	}

	/**
	 * Test method for {@link Color#near(Color, int)}.
	 */
	@Test
	public void testNear() {
		Color c1, c2, c3;

		c1 = new Color(255, 255, 255);
		c2 = new Color(127, 220, 150);
		c3 = new Color(75, 120, 0);
		
		assertTrue(c1.near(c1, 1));
		assertTrue(c1.near(c2, 130));
		assertFalse(c1.near(c2, 100));
		assertFalse(c1.near(c3, 200));
	}
}
