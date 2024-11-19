package fr.insa_rennes.sdd.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LeftToRightGridGraphTest {

	@Test
	void testNumberOfVertices() {
		double[][] grid = {{1, 1, 1}, {1, 1, 1}};
		LeftToRightGridGraph g = new LeftToRightGridGraph(grid);
		assertEquals(8, g.numberOfVertices());
		grid = new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
		g = new LeftToRightGridGraph(grid);
		assertEquals(22, g.numberOfVertices());
	}
	
	@Test
	void testNumberOfEdges() {
		double[][] grid = {{1, 1, 1}, {1, 1, 1}};
		LeftToRightGridGraph g = new LeftToRightGridGraph(grid);
		assertEquals(12, g.numberOfEdges());
		grid = new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
		g = new LeftToRightGridGraph(grid);
		assertEquals(49, g.numberOfEdges());
	}
	
	@Test
	void testNeighbors() {
		double[][] grid = {{10, 1, 42}, {0, 100, 1000}, {-5, -4, -3}};
		LeftToRightGridGraph g = new LeftToRightGridGraph(grid);
		Set<VertexAndWeight<Coordinate>> neighbors = new HashSet<>();
		neighbors.add(new VertexAndWeight<>(new Coordinate(0, 0), 10));
		neighbors.add(new VertexAndWeight<>(new Coordinate(1, 0), 0));
		neighbors.add(new VertexAndWeight<>(new Coordinate(2, 0), -5));
		Set<VertexAndWeight<Coordinate>> expected = new HashSet<>();
		g.neighbors(Coordinate.LEFT).forEach(expected::add);
		assertEquals(expected, neighbors);
		
		neighbors.clear();
		neighbors.add(new VertexAndWeight<>(new Coordinate(0, 2), 42));
		neighbors.add(new VertexAndWeight<>(new Coordinate(1, 2), 1000));
		expected.clear();
		g.neighbors(new Coordinate(0, 1)).forEach(expected::add);
		assertEquals(expected, neighbors);
		
		neighbors.clear();
		neighbors.add(new VertexAndWeight<>(new Coordinate(0, 2), 42));
		neighbors.add(new VertexAndWeight<>(new Coordinate(1, 2), 1000));
		neighbors.add(new VertexAndWeight<>(new Coordinate(2, 2), -3));
		expected.clear();
		g.neighbors(new Coordinate(1, 1)).forEach(expected::add);
		assertEquals(expected, neighbors);
		
		neighbors.clear();
		neighbors.add(new VertexAndWeight<>(Coordinate.RIGHT, 0));		
		expected.clear();
		g.neighbors(new Coordinate(2, 2)).forEach(expected::add);
		assertEquals(expected, neighbors);
		
		neighbors.clear();
		neighbors.add(new VertexAndWeight<>(new Coordinate(1, 1), 100));
		neighbors.add(new VertexAndWeight<>(new Coordinate(2, 1), -4));
		expected.clear();
		g.neighbors(new Coordinate(2, 0)).forEach(expected::add);
		assertEquals(expected, neighbors);
	}

}
