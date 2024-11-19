package fr.insa_rennes.sdd.priority_queue;

public interface PriorityQueue<T> {
	boolean isEmpty();
	int size();
	void add(T e);
	T peek();
	T poll();	
}
