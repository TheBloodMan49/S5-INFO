import java.io.Serial;

/**
 * Color
 * <p>
 * Class implementing the colors.
 */
public class Color extends java.awt.Color {

	/**
	 * <code>serialVersionUID</code> : Serialization version number.
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Color constructor.
	 * 
	 * @param r Red component.
	 * @param g Green component.
	 * @param b Blue component.
	 * @param a Alpha component.
	 */
	public Color(final int r, final int g, final int b, final int a) {
		super(r, g, b, a);
	}

	/**
	 * Color constructor.
	 * 
	 * @param r Red component.
	 * @param g Green component.
	 * @param b Blue component.
	 */
	public Color(final int r, final int g, final int b) {
		super(r, g, b);
	}

	/**
	 * Color constructor.
	 * 
	 * @param rgb RGB color.
	 */
	public Color(final int rgb) {
		super(rgb);
	}

	/**
	 * Test if two colors are the same.
	 * 
	 * @param c The color to compare this color with.
	 * @return true if the colors are the same, false otherwise.
	 * @see java.awt.Color#equals(Object)
	 */
	public boolean equals(Object c) {
		return super.equals(c);
	}
	
	/**
	 * Generate a string representation of the color.
	 * 
	 * @return The generated string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final Object[] colors = {
                this.getRed(),
                this.getGreen(),
                this.getBlue(),
                this.getAlpha(),
		};
		return String.format("(%3d, %3d, %3d, %3d)", colors); //$NON-NLS-1$
	}
	
	/**
	 * Compute the Euclidean distance between two colors.
	 * 
	 * @param c The second color.
	 * @return A new color composed by the distances between each component
	 *   of the original colors.
	 */
	public Color distance(final Color c) {
		return (c == null ? null :
			new Color(
				Math.abs(this.getRed()   - c.getRed()),
				Math.abs(this.getGreen() - c.getGreen()),
				Math.abs(this.getBlue()  - c.getBlue()),
				Math.abs(this.getAlpha() - c.getAlpha())
			)
		);
	}

	/**
	 * Compute the average color between two colors.
	 * 
	 * @param c The second color.
	 * @param weight The weight of the current color compared to c. This value
	 *   must be bounded between 0.0 and 1.0
	 * @return The average color.
	 */
	public Color average(final Color c, final double weight) {
		return (c == null || weight < 0 || weight > 1 ? null :
			new Color(
				(int) (weight * this.getRed()   + (1 - weight) * c.getRed()),
				(int) (weight * this.getGreen() + (1 - weight) * c.getGreen()),
				(int) (weight * this.getBlue()  + (1 - weight) * c.getBlue()),
				(int) (weight * this.getAlpha() + (1 - weight) * c.getAlpha())
			)
		);
	}

	/**
	 * Test if two colors are close.
	 * 
	 * @param c The second color.
	 * @param threshold The threshold to make the comparison.
	 * @return true if the distances between each component of the colors are
	 *   under the threshold, false otherwise.
	 */
	public boolean near(final Color c, final int threshold) {
		final Color d = this.distance(c);
		return (d != null && (
                d.getRed() <= threshold &&
                        d.getGreen() <= threshold &&
                        d.getBlue() <= threshold &&
                        d.getAlpha() <= threshold
        )
		);
	}

}// class Color
