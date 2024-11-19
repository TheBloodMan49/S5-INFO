package fr.insa_rennes.sdd.seam_carving;

import java.io.File;
import java.util.function.Function;

import fr.insa_rennes.sdd.graph.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Picture {
	private final Image image;
	
	public Picture(Image image) {
		this.image = image;
	}
	
	public Picture(String uri) {
		image = new Image(uri);
	}
	
	public Picture(File file) {
		image = new Image(file.toURI().toString());
	}
	
	public Picture(double[][] red, double[][] green, double[][] blue) {
		int w = red[0].length;
		int h = red.length;		
		WritableImage image = new WritableImage(w, h);
		PixelWriter writer = image.getPixelWriter();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {			
				writer.setColor(x, y, new Color(red[y][x], green[y][x], blue[y][x], 1));
			}
		}
		this.image = image;
	}
		
	private double[][] getPlane(Function<Color, Double> select) {
		PixelReader reader = image.getPixelReader();
		int w = (int)image.getWidth();
		int h = (int)image.getHeight();
		double[][] plane = new double[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color c = reader.getColor(x, y);
				plane[y][x] = select.apply(c);
			}
		}
		return plane;
	}
	
	public double[][] redPlane() {
		return getPlane(Color::getRed);
	}
	
	public double[][] greenPlane() {
		return getPlane(Color::getGreen);
	}
	
	public double[][] bluePlane() {
		return getPlane(Color::getBlue);
	}
	
	public int height() {
		return (int)image.getHeight();
	}
	
	public int width() {
		return (int)image.getWidth();
	}	
	
	public Color color(int row, int col) {
		return image.getPixelReader().getColor(col, row);
	}
	
	public Color color(Coordinate pixel) {
		return color(pixel.row, pixel.col);
	}
	
	public Image image() {
		return image;
	}
}
