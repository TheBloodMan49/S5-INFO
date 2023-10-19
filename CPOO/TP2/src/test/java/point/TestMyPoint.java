package point;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMyPoint {
	// alt + insert
	private MyPoint pt;
	@BeforeEach
	void setUp() {
		pt = new MyPoint();
	}

	@Test
	void constructorDefault() {
		assertEquals(0, pt.getX(), 0.001);
		assertEquals(0, pt.getY(), 0.001);
	}

	@Test
	void setPointRandom() {
		final Random random = Mockito.mock(Random.class); //new Random();
		Mockito.when(random.nextInt()).thenReturn(1, 2);

		pt.setPoint(random, random);
		assertEquals(1, pt.getX(), 0.0001);
		assertEquals(2, pt.getY(), 0.0001);
	}

	@Test
	void testX() {
		pt.setX(5);
		assertEquals(5, pt.getX(), 0.001);
	}

	@Test
	void testY() {
		pt.setY(5);
		assertEquals(5, pt.getY(), 0.001);
	}

	@Test
	void testXNaN() {
		pt.setX(Double.NaN);
		assertEquals(Double.NaN, pt.getX(), 0.001);
	}

	@Test
	void testYNaN() {
		pt.setY(Double.NaN);
		assertEquals(Double.NaN, pt.getY(), 0.001);
	}

	@Test
	void contructor3() {
		final MyPoint pt = new MyPoint(new MyPoint(1, 2));
		assertEquals(1, pt.getX(), 0.001);
		assertEquals(2, pt.getY(), 0.001);
	}

	@Test
	void testScale() {
		final MyPoint pt = new MyPoint(1, 2);
		MyPoint pt2 = pt.scale(2);
		assertEquals(2, pt2.getX(), 0.001);
		assertEquals(4, pt2.getY(), 0.001);
	}

	@Test
	void symmetry() {
		final MyPoint pt = new MyPoint(1, 2);
		MyPoint pt2 = pt.horizontalSymmetry(new MyPoint(0, 0));
		assertEquals(1, pt2.getX(), 0.001);
		assertEquals(-2, pt2.getY(), 0.001);
	}

	@Test
	void symmetryThrows() {
		final MyPoint pt = new MyPoint(1, 2);
		assertThrows(IllegalArgumentException.class, () -> pt.horizontalSymmetry(null));
	}

	@Test
	void testTranslate() {
		final MyPoint pt = new MyPoint(1, 2);
		final ITranslation translation = Mockito.mock(ITranslation.class);
		Mockito.when(translation.getTx()).thenReturn(1);
		Mockito.when(translation.getTy()).thenReturn(2);
		pt.translate(translation);
		assertEquals(2, pt.getX(), 0.001);
		assertEquals(4, pt.getY(), 0.001);
	}

	@Test
	void testTranslateCoord() {
		final MyPoint pt = new MyPoint(1, 2);
		pt.translate(1, 2);
		assertEquals(2, pt.getX(), 0.001);
		assertEquals(4, pt.getY(), 0.001);
	}
}
