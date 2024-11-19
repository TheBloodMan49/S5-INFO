package fr.insa_rennes.sdd.seam_carving;

import java.util.Deque;
import java.util.function.BiFunction;
import fr.insa_rennes.sdd.dijkstra.Dijkstra;
import fr.insa_rennes.sdd.graph.Coordinate;
import fr.insa_rennes.sdd.graph.LeftToRightGridGraph;
import fr.insa_rennes.sdd.graph.TopToBottomGridGraph;

public class SeamCarverDijkstra extends SeamCarver {
	
	public SeamCarverDijkstra(Picture picture) {
		super(picture);		
	}
	
	public SeamCarverDijkstra(Picture picture, BiFunction<Double, Double, Double> energyFunction) {
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
