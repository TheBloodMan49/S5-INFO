package fr.insa_rennes.sdd.dijkstra;

import fr.insa_rennes.sdd.priority_queue.HeapPQ;
import fr.insa_rennes.sdd.priority_queue.OrderedArrayPQ;
import fr.insa_rennes.sdd.priority_queue.PriorityQueue;

public class FactoryPQ {
	public static <T> PriorityQueue<DijkstraNode<T>> newInstance(String pq) {
		switch (pq) {
		case "OrderedArrayPQ":
			return new OrderedArrayPQ<>();
		case "HeapPQ":
			return new HeapPQ<>();
		default:
			throw new IllegalArgumentException();
		}
	}
}
