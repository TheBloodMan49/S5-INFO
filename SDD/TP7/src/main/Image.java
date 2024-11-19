import java.io.IOException;
import java.util.Arrays;

/**
 * Image
 * <p>
 * Class Implementing basic image IO
 */
public class Image {

	/**
	 * <code>WHITE</code> : Value corresponding to the white color.
	 */
	private final static Color WHITE = new Color(255, 255, 255);
	
	/**
	 * <code>image</code>: an array of Colors representing the image.
	 */
	protected Color[][] image;
	
	/**
	 * Image constructor
	 * 
	 * @param size The size of the new image.
	 * @throws IllegalArgumentException If the size is not a power of two.
	 */
	public Image(int size) throws IllegalArgumentException{
		if(! power2(size)) {
			throw new IllegalArgumentException("The size must be a power of two."); //$NON-NLS-1$
		}
		
		// Create the array representation
		this.image = new Color[size][size];

        for (Color[] colors : this.image) {
            // For each pixel, we initialize the array with the white Color.
            Arrays.fill(colors, WHITE);
        }
	}
	
	/**
	 * Image constructor
	 * 
	 * @param name The name of the image file that must be read.
	 *   The name can include relative or absolute path.
	 * @throws IOException If the file can not be read.
	 * @throws IllegalArgumentException If the image is not squared with
	 *   a resolution power of two.
	 */
	public Image(String name) throws IOException, IllegalArgumentException {
		int width, height;
		java.io.File file;
		java.awt.image.BufferedImage buffer;
		
		// Open the file
		file = new java.io.File(name);
		// Read the data
		buffer = javax.imageio.ImageIO.read(file);
		
		// Get the image dimensions
		width = buffer.getWidth();
		height = buffer.getHeight();
		
		// Check if the image size is a power of two
		if (! power2(width) || width != height) {
			throw new IllegalArgumentException("Only square images with a power of two resolution are suported"); //$NON-NLS-1$
		}
		
		// Create the array representation
		this.image = new Color[width][height];
		
		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image[i].length; j++) {
				/*
				 * For each pixel, we initialize the array with
				 * the correct Color
				 */
				this.image[i][j] = new Color(buffer.getRGB(i, j));
			}
		}		
	}
	
	/**
	 * Provide the resolution in pixels of the image (the image is square,
	 *   which means that the width and height has the same number of pixel).
	 * 
	 * @return The resolution of the image.
	 */
	public int getSize() {
		return this.image.length;
	}
	
	/**
	 * Provide the resolution in pixels of the width of the image.
	 * 
	 * @return The width of the image.
	 */
	public int getWidth() {
		return getSize();
	}
	
	/**
	 * Provide the resolution in pixels of the height of the image.
	 * 
	 * @return The height of the image.
	 */
	public int getHeight() {
		return getSize();
	}
	
	/**
	 * Provide access to a given pixel of the image.
	 * 
	 * @param i The width coordinate.
	 * @param j The height coordinate.
	 * @return The Color corresponding to the pixel.
	 * @throws IllegalArgumentException If the coordinates are not valid.
	 */
	public Color getPixel(int i, int j) throws IllegalArgumentException {
		if(i < 0 || j < 0 || i >= getWidth() || j >= getHeight()) {
			throw new IllegalArgumentException("The coordinates are not valid."); //$NON-NLS-1$
		}
		
		return this.image[i][j];
	}
	
	/**
	 * Set the value of a given pixel.
	 * 
	 * @param i The width coordinate.
	 * @param j The height coordinate.
	 * @param c The value of the pixel.
	 * @throws IllegalArgumentException If the coordinates are not valid.
	 */
	public void setPixel(int i, int j, Color c) throws IllegalArgumentException {
		if(i < 0 || j < 0 || i >= getWidth() || j >= getHeight()) {
			throw new IllegalArgumentException("The coordinates are not valid."); //$NON-NLS-1$
		}
		
		this.image[i][j] = c;
	}
	
	/**
	 * Save the image as a standard image file.
	 * 
	 * @param name The name of the file (with eventually relative or
	 *   absolute path).
	 * @param format The image type (examples : bmp, jpg, giff...)
	 * @throws IOException If the file can not be written.
	 */
	public void save(String name, String format) throws IOException {
		java.io.File file;
		java.awt.image.BufferedImage buffer;
		
		// Create a new buffer to store the pixel values.
		buffer = new java.awt.image.BufferedImage(getWidth(), getHeight(),
				java.awt.image.BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image[i].length; j++) {
				/*
				 * For each pixel, we initialize the buffer with the correct
				 * color value.
				 */
				buffer.setRGB(i, j, this.image[i][j].getRGB());
			}
		}
		
		// Open the output file.
		file = new java.io.File(name);
		// Write the content of the buffer into the file.
		javax.imageio.ImageIO.write(buffer, format, file);
	}
	
	/**
	 * Test if a number is a power of two.
	 * 
	 * @param n The number that must be checked.
	 * @return true if n is a power of two, false otherwise.
	 */
	public static boolean power2(int n) {
		int q = n;

		while (q > 2 && (q % 2 == 0)) {
			q = q / 2;
		}

		return (q % 2 == 0);
	}
	
} // Class Image
