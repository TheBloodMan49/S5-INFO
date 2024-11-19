package fr.insa_rennes.sdd.dijkstra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.insa_rennes.sdd.graph.Coordinate;
import fr.insa_rennes.sdd.graph.Graph;
import fr.insa_rennes.sdd.graph.IndexedGraph;
import fr.insa_rennes.sdd.graph.LeftToRightGridGraph;
import fr.insa_rennes.sdd.graph.TopToBottomGridGraph;

class DijkstraTest {

	@Test
	void testOnIndexedGraph() {
		Graph<Integer> g = new IndexedGraph(9);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 3, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(1, 4, 1);
		g.addEdge(3, 4, 1);
		g.addEdge(3, 6, 1);
		g.addEdge(4, 5, 1);
		g.addEdge(4, 7, 1);
		g.addEdge(5, 8, 1);
		g.addEdge(6, 7, 1);
		g.addEdge(7, 8, 1);
		Dijkstra<Integer> dijkstra = new Dijkstra<>(g, 0);
		double[] dist = { 0, 1, 2, 1, 2, 3, 2, 3, 4 };
		for (int i = 0; i < g.numberOfVertices(); i++) {
			assertTrue(dijkstra.hasPathTo(i));
			assertEquals(dist[i], dijkstra.getCost(i));
		}
		
		g = new IndexedGraph(6);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 10);
		g.addEdge(1, 4, 1);
		g.addEdge(2, 3, 10);
		g.addEdge(4, 5, 100);
		g.addEdge(3, 5, 10);
		dijkstra = new Dijkstra<>(g, 0);
		dist = new double[]{ 0, 1, 11, 21, 2, 31 };
		for (int i = 0; i < g.numberOfVertices(); i++) {
			assertTrue(dijkstra.hasPathTo(i));
			assertEquals(dist[i], dijkstra.getCost(i));
		}
		List<Integer> path = new ArrayList<>();
		dijkstra.getPathTo(5).forEach(path::add);
		Iterable<Integer> correctPath = List.of(0, 1, 2, 3, 5);		
		assertEquals(correctPath, path);
	}
	
	@Test
	void testOnLeftToRightGridGraph() {
		double[][] grid = {{10, 1, 420}, {90, 100, 1000}, {500, 300, 2}};
		LeftToRightGridGraph g = new LeftToRightGridGraph(grid);
		Dijkstra<Coordinate> dijkstra = new Dijkstra<>(g, Coordinate.LEFT);
		assertTrue(dijkstra.hasPathTo(Coordinate.RIGHT));
		assertEquals(112, dijkstra.getCost(Coordinate.RIGHT));
		List<Coordinate> path = new ArrayList<>();
		dijkstra.getPathTo(Coordinate.RIGHT).forEach(path::add);
		Iterable<Coordinate> correctPath = List.of(Coordinate.LEFT, 
				new Coordinate(0, 0), new Coordinate(1, 1), new Coordinate(2, 2), 
				Coordinate.RIGHT);		
		assertEquals(correctPath, path);
	}

	@Test
	void testOnTopToDownGridGraph() {
		double[][] grid = {{100, 1, 420}, {90, 100, 1000}, {500, 300, 2}};
		TopToBottomGridGraph g = new TopToBottomGridGraph(grid);
		Dijkstra<Coordinate> dijkstra = new Dijkstra<>(g, Coordinate.TOP);
		assertTrue(dijkstra.hasPathTo(Coordinate.BOTTOM));
		assertEquals(103, dijkstra.getCost(Coordinate.BOTTOM));
		List<Coordinate> path = new ArrayList<>();
		dijkstra.getPathTo(Coordinate.BOTTOM).forEach(path::add);
		Iterable<Coordinate> correctPath = List.of(Coordinate.TOP, 
				new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(2, 2), 
				Coordinate.BOTTOM);		
		assertEquals(correctPath, path);
	}
}
