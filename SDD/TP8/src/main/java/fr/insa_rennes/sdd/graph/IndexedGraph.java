package fr.insa_rennes.sdd.graph;

import java.util.ArrayList;
import java.util.List;


public class IndexedGraph implements Graph<Integer> {
	private final int numberOfVertices;
	private int numberOfEdges;
	private boolean oriented;
	private List<VertexAndWeight<Integer>>[] adjacency;
	
	public IndexedGraph(int numberOfVertices) {
		this(numberOfVertices, true);
	}
	
	@SuppressWarnings("unchecked")
	public IndexedGraph(int numberOfVertices, boolean oriented) {
		this.numberOfVertices = numberOfVertices;
		this.oriented = oriented;
		adjacency = new ArrayList[numberOfVertices];
		for (int i = 0; i < numberOfVertices; i++) {
			adjacency[i] = new ArrayList<>();
		}
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
	public void addVertex(Integer v) {
		throw new UnsupportedOperationException("In an IndexedGraph, all vertices are created at initialization");		
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= numberOfVertices) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	@Override
	public void addEdge(Integer u, Integer v, double weight) {
		validateVertex(u);
		validateVertex(v);
		adjacency[u].add(new VertexAndWeight<>(v, weight));
		if (!oriented) {
			adjacency[v].add(new VertexAndWeight<>(u, weight));
		}
	}

	@Override
	public Iterable<VertexAndWeight<Integer>> neighbors(Integer u) {
		validateVertex(u);
		return adjacency[u];
	}

}
