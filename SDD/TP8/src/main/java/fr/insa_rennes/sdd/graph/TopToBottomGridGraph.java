package fr.insa_rennes.sdd.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopToBottomGridGraph implements Graph<Coordinate> {
	private final double[][] grid;
	private int numberOfVertices;
	private int numberOfEdges;
	
	public TopToBottomGridGraph(double[][] grid) {
		this.grid = grid;
		int h = grid.length;
		int w = grid[0].length;
		this.numberOfVertices = h * w + 2;
		this.numberOfEdges = h * w * 3 - w - h * 2 + 2;
	}
	
	@Override
	public int numberOfVertices() {
		return numberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		return numberOfEdges;
	}

	@Override
	public void addVertex(Coordinate v) {
		throw new UnsupportedOperationException("In an UpToDownGridGraph, all vertices are created at initialization");
	}
	
	@Override
	public void addEdge(Coordinate u, Coordinate v, double weight) {
		throw new UnsupportedOperationException("In an UpToDownGridGraph, all edges are created at initialization");
	}

	//private Map<Coordinate, List<VertexAndWeight<Coordinate>>> memo = new HashMap<>(); 	
	@Override
	public Iterable<VertexAndWeight<Coordinate>> neighbors(Coordinate u) {
		//validateVertex(u);
		List<VertexAndWeight<Coordinate>> res = new ArrayList<>();//memo.get(u); 
		/*if (res != null) {
			return res;
		}*/
		//res = new ArrayList<>();		
		int h = grid.length;
		int w = grid[0].length;
		if (u.equals(Coordinate.TOP)) {			
			for (int col = 0; col < w; col++) {
				res.add(new VertexAndWeight<>(new Coordinate(0, col), grid[0][col]));
			}
		} else if (u.equals(Coordinate.BOTTOM)) {			
		} else if (u.row == h - 1) {
			res.add(new VertexAndWeight<>(Coordinate.BOTTOM, 0));	
		} else {
			if (u.col != 0) {
				res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, u.col - 1), grid[u.row + 1][u.col - 1]));
			} //else {
				//res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, w - 1), grid[u.row + 1][w - 1]));
			//}
			res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, u.col), grid[u.row + 1][u.col]));
			if (u.col != w - 1) {
				res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, u.col + 1), grid[u.row + 1][u.col + 1]));
			} //else {
				//res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, 0), grid[u.row + 1][u.col + 1]));
			//}
		}
		//memo.put(u, res);
		return res;
	}
/*
	private void validateVertex(Coordinate u) {
		if (u.equals(Coordinate.TOP) || u.equals(Coordinate.BOTTOM)) {
			return;
		}
		if (u.row < 0 || u.row >= grid.length || 
			u.col < 0 || u.col >= grid[0].length) {
			throw new IndexOutOfBoundsException();
		}		
	}
*/
}
