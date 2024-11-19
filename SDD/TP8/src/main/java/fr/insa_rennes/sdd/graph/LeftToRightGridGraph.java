package fr.insa_rennes.sdd.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeftToRightGridGraph implements Graph<Coordinate> {
	private final double[][] grid;
	private int numberOfVertices;
	private int numberOfEdges;
	
	public LeftToRightGridGraph(double[][] grid) {
		this.grid = grid; 
		int h = grid.length;
		int w = grid[0].length;
		this.numberOfVertices = h * w + 2;
		this.numberOfEdges = h * w * 3 - h - w * 2 + 2;
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
		throw new UnsupportedOperationException("In a LeftToRightGridGraph, all vertices are created at initialization");
	}
	
	@Override
	public void addEdge(Coordinate u, Coordinate v, double weight) {
		throw new UnsupportedOperationException("In a LeftToRightGridGraph, all edges are created at initialization");
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
		if (u.equals(Coordinate.LEFT)) {			
			for (int row = 0; row < h; row++) {
				res.add(new VertexAndWeight<>(new Coordinate(row, 0), grid[row][0]));
			}
		} else if (u.equals(Coordinate.RIGHT)) {			
		} else if (u.col == w - 1) {
				res.add(new VertexAndWeight<>(Coordinate.RIGHT, 0));	
		} else {
			if (u.row != 0) {
				res.add(new VertexAndWeight<>(new Coordinate(u.row - 1, u.col + 1), grid[u.row - 1][u.col + 1]));
			} //else {
				//res.add(new VertexAndWeight<>(new Coordinate(h - 1, u.col + 1), grid[h - 1][u.col + 1]));
			//}
			res.add(new VertexAndWeight<>(new Coordinate(u.row, u.col + 1), grid[u.row][u.col + 1]));
			if (u.row != h - 1) {
				res.add(new VertexAndWeight<>(new Coordinate(u.row + 1, u.col + 1), grid[u.row + 1][u.col + 1]));
			} //else {
				//res.add(new VertexAndWeight<>(new Coordinate(0, u.col + 1), grid[0][u.col + 1]));
			//}
		}
		//memo.put(u, res);
		return res;
	}
/*
	private void validateVertex(Coordinate u) {
		if (u.equals(Coordinate.LEFT) || u.equals(Coordinate.RIGHT)) {
			return;
		}
		if (u.row < 0 || u.row >= grid.length || 
			u.col < 0 || u.col >= grid[0].length) {
			throw new IndexOutOfBoundsException();
		}		
	}
*/
}
