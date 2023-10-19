package point;

import java.util.Random;

/**
 * A Basic point with double values.
 * @author ablouin
 */
public class MyPoint {
	private double x;
	private double y;


	/**
	 * Constructor 1
	 * Creates a MyPoint with coordinates (0, 0).
	 */
	public MyPoint() {
		this(0, 0);
	}


	/**
	 * Constructor 2
	 * Creates a MyPoint with the specified coordinates.
	 * @param x The X-coordinate to set.
	 * @param y The Y-coordinate to set.
	 */
	public MyPoint(final double x, final double y) {
		super();
		this.x = x;
		this.y = y;
	}


	/**
	 * Constructor 3
	 * Creates a point from a IMyPoint.
	 * (0,0) will be used when the given pt is null.
	 * @param pt The IMyPoint, if null the default value (0,0) will be used.
	 */
	public MyPoint(final MyPoint pt) {
		this(pt.x, pt.y);
	}

	/**
	 * @return The X coordinate of the point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X coordinate of the point.
	 * @param newX The new X coordinate. Must be valid (not equal Double.NaN), otherwise nothing is done.
	 */
	public void setX(final double newX) {
		x = newX;
	}

	/**
	 * @return The Y coordinate of the point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate of the point.
	 * @param newY The new Y coordinate. Must be valid (not equal Double.NaN), otherwise nothing is done.
	 */
	public void setY(final double newY) {
		y = newY;
	}

	/**
	 * Creates a new point scaled using the scaling factor.
	 * @param sx The scaling factor.
	 * @return The scaled point.
	 * @since 3.0
	 */
	public MyPoint scale(final double sx) {
		return new MyPoint(x * sx, y * sx);
	}

	/**
	 * Returns horizontally the point.
	 * @param origin The location of the horizontal axe.
	 * @return the computed point.
	 * @throws IllegalArgumentException When the given parameter is null.
	 */
	public MyPoint horizontalSymmetry(final MyPoint origin) {
		if(origin == null) {
			throw new IllegalArgumentException();
		}
		return new MyPoint(x,2. * origin.getY() - y);
	}


	/**
	 * Translates the point.
	 * If one of the given coordinate is not valid (Double.NaN), the translation does not occur.
	 * @param tx The X translation.
	 * @param ty The Y translation.
	 */
	public void translate(final double tx, final double ty) {
		setX(x + tx);
		setY(y + ty);
	}


	/**
	 * Sets a point using random values provided by random.newInt().
	 * @param random1 The random number generator used for x.
	 * @param random2 The random number generator used for y.
	 */
	public void setPoint(final Random random1, final Random random2) {
		setX(random1.nextInt());
		setY(random2.nextInt());
	}


	/**
	 * Translates the point using a given vector.
	 * @param translation The translation vector. If null, nothing is performed.
	 */
	public void translate(final ITranslation translation) {
		if(translation != null) {
			translate(translation.getTx(), translation.getTy());
		}
	}
}
