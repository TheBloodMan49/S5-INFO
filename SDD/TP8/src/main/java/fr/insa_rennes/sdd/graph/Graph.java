package fr.insa_rennes.sdd.graph;

public interface Graph<T> {
	int numberOfVertices();
	int numberOfEdges();
	void addVertex(T v);
	void addEdge(T u, T v, double weight);
	Iterable<VertexAndWeight<T>> neighbors(T u);	
}
