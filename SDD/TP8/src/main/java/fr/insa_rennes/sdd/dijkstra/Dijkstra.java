package fr.insa_rennes.sdd.dijkstra;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import fr.insa_rennes.sdd.graph.Graph;
import fr.insa_rennes.sdd.graph.VertexAndWeight;
import fr.insa_rennes.sdd.priority_queue.PriorityQueue;

public class Dijkstra<T> {
	private final PriorityQueue<DijkstraNode<T>> pq;	
	private final Map<T, Double> cost = new HashMap<>();
	private final Map<T, T> prev = new HashMap<>();

	public Dijkstra(Graph<T> graph, T source) {
		this(graph, source, FactoryPQ.newInstance("HeapPQ"));
	}	

	public Dijkstra(Graph<T> graph, T source, PriorityQueue<DijkstraNode<T>> pq) {
		this.pq = pq; 
		solve(graph, source);
	}

	private void solve(Graph<T> graph, T source) {
		while (!pq.isEmpty()) {
			DijkstraNode<T> node = pq.poll();
			T u = node.vertex;
			if (cost.containsKey(u)) {
				continue;
			}
			cost.put(u, node.cost);
			prev.put(u, node.prev);
			for (VertexAndWeight<T> vw : graph.neighbors(u)) {
				T v = vw.vertex;
				double w = vw.weight;
				if (!cost.containsKey(v)) {
					pq.add(new DijkstraNode<>(node.cost + w, v, u));
				}
			}
		}
	}

	public Deque<T> getPathTo(T v) {
		throw new UnsupportedOperationException();
	}

	public double getCost(T v) {
		return cost.getOrDefault(v, Double.POSITIVE_INFINITY);
	}
	
	public boolean hasPathTo(T v) {
		return getCost(v) != Double.POSITIVE_INFINITY;
	}

}
