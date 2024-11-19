package fr.insa_rennes.sdd.seam_carving;

import java.util.Deque;
import java.util.function.BiFunction;

import fr.insa_rennes.sdd.graph.Coordinate;
import javafx.scene.paint.Color;

public abstract class SeamCarver {
	protected Picture picture;
	protected BiFunction<Double, Double, Double> energyFunction;
	
	protected SeamCarver(Picture picture) {
		this(picture, (v1, v2) -> (v1 - v2) * (v1 - v2));		
	}
	
	protected SeamCarver(Picture picture, BiFunction<Double, Double, Double> energyFunction) {
		this.picture = picture;
		this.energyFunction = energyFunction;
	}
	
	public Picture picture() {
		return picture;
	}

	public abstract void reduceToSize(int width, int height);	
	public abstract Deque<Coordinate> horizontalSeam();		
	public abstract Deque<Coordinate> verticalSeam();
	
	protected void cropHorizontal(Deque<Coordinate> seam) {
		int w = picture.width();
		int h = picture.height();
		double[][] red = new double[h - 1][w]; 
		double[][] green = new double[h - 1][w];
		double[][] blue = new double[h - 1][w];
		for (int col = 0; col < w; col++) {
			int r = seam.removeFirst().row;
			int k = 0;
			for (int row = 0; row < h; row++) {
				if (row != r) {					
					Color color = picture.color(row, col);
					red[k][col] = color.getRed();
					green[k][col] = color.getGreen();
					blue[k][col] = color.getBlue();
					k++;
				}				
			}
		}
		picture = new Picture(red, green, blue);		
	}

	protected void cropVertical(Deque<Coordinate> seam) {
		int w = picture.width();
		int h = picture.height();
		double[][] red = new double[h][w - 1]; 
		double[][] green = new double[h][w - 1];
		double[][] blue = new double[h][w - 1];
		for (int row = 0; row < h; row++) {
			int c = seam.removeFirst().col;
			int k = 0;
			for (int col = 0; col < w; col++) {
				if (col != c) {					
					Color color = picture.color(row, col);
					red[row][k] = color.getRed();
					green[row][k] = color.getGreen();
					blue[row][k] = color.getBlue();
					k++;
				}				
			}
		}
		picture = new Picture(red, green, blue);		
	}

	protected double[][] energyMap() {
		double[][] redEnergy = energy(picture.redPlane());
		double[][] greenEnergy = energy(picture.greenPlane());
		double[][] blueEnergy = energy(picture.bluePlane());
		double[][] res = blueEnergy;
		for (int row = 0; row < res.length; row++) {
			for (int col = 0; col < res[0].length; col++) {
				res[row][col] += redEnergy[row][col] + greenEnergy[row][col];
			}
		}
		return res;
	}
	
	protected double[][] energy(double[][] channel) {
		int h = channel.length;
		int w = channel[0].length;
		double[][] res = new double[h][w];
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				double v = channel[row][col];
				double delta = row == 0 ? 0 : energyFunction.apply(v, channel[row - 1][col]);
				res[row][col] += delta;
				delta = row == h - 1 ? 0 : energyFunction.apply(v, channel[row + 1][col]);
				res[row][col] += delta;
				delta = col == 0 ? 0 : energyFunction.apply(v, channel[row][col - 1]);
				res[row][col] += delta;
				delta = col == w - 1 ? 0 : energyFunction.apply(v, channel[row][col + 1]);
				res[row][col] += delta;
			}
		}		
		return res;
	}
}
