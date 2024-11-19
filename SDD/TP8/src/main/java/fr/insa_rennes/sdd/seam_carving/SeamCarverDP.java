package fr.insa_rennes.sdd.seam_carving;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

import fr.insa_rennes.sdd.graph.Coordinate;

public class SeamCarverDP extends SeamCarver {
	
	public SeamCarverDP(Picture picture) {
		super(picture);		
	}
	
	public SeamCarverDP(Picture picture, BiFunction<Double, Double, Double> energyFunction) {
		super(picture, energyFunction);
	}
	
	@Override
	public void reduceToSize(int width, int height) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Deque<Coordinate> horizontalSeam() {		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Deque<Coordinate> verticalSeam() {
		throw new UnsupportedOperationException();
	}
	
}
