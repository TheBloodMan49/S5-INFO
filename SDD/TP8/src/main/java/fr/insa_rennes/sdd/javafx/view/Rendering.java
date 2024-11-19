package fr.insa_rennes.sdd.javafx.view;

import java.util.Deque;
import java.util.Optional;
import fr.insa_rennes.sdd.graph.Coordinate;
import fr.insa_rennes.sdd.seam_carving.SeamCarver;
import fr.insa_rennes.sdd.util.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Rendering {
	private static final Color BACKGROUND_COLOR = Color.rgb(44, 68, 43);
	private static final Color SEAM_COLOR = Color.ORANGE;
	private static final double SEAM_SIZE = 4;

	public static void draw(GraphicsContext gc, Optional<SeamCarver> seamCarverOption, 
						    double zoomRatio, boolean drawHorizontalSeam, boolean drawVerticalSeam) {
		double width = gc.getCanvas().getWidth();
		double height = gc.getCanvas().getHeight();
		gc.setFill(BACKGROUND_COLOR);
		gc.fillRect(0, 0, width, height);
		if (seamCarverOption.isEmpty()) {
			return;
		}
		SeamCarver seamCarver = seamCarverOption.get(); 
		Image image = seamCarver.picture().image();		
		double x = 0;
		double widthZoom = image.getWidth() * zoomRatio;
		double heightZoom = image.getHeight() * zoomRatio;
		if (widthZoom < width) {
			x = (width - widthZoom) / 2;
		}
		double y = 0;
		if (heightZoom < height) {
			y = (height - heightZoom) / 2;
		}
		gc.drawImage(image, x, y, widthZoom, heightZoom);
		if (drawHorizontalSeam) {		
			drawSeam(gc, seamCarver.horizontalSeam(), x, y, zoomRatio);
		}
		if (drawVerticalSeam) {		
			drawSeam(gc, seamCarver.verticalSeam(), x, y, zoomRatio);
		}
	}
	
	private static void drawSeam(GraphicsContext gc, Deque<Coordinate> seam, 
								 double x, double y, double zoomRatio) {
		gc.setFill(SEAM_COLOR);
		seam.stream()
			.map(c -> new Pair<Double, Double>(c.col * zoomRatio + x, c.row * zoomRatio + y))
			.forEach(c -> gc.fillOval(c.first, c.second, SEAM_SIZE, SEAM_SIZE));
	}
}
