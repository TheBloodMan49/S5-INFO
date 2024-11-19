package fr.insa_rennes.sdd.priority_queue;

import java.util.Arrays;
import java.util.Comparator;

public class HeapPQ<T> implements PriorityQueue<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 8;
	private Comparator<? super T> comparator;
	private int size;
	T[] heap;

	public HeapPQ() {
		this(DEFAULT_INITIAL_CAPACITY, null);
	}	

	public HeapPQ(int initialCapacity) {
		this(initialCapacity, null);
	}

	public HeapPQ(Comparator<? super T> comparator) {
		this(DEFAULT_INITIAL_CAPACITY, comparator);
	}

	@SuppressWarnings("unchecked")
	public HeapPQ(int initialCapacity, Comparator<? super T> comparator) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		heap = (T[]) new Object[initialCapacity];
		this.comparator = comparator == null ? (t1, t2) -> ((Comparable<? super T>)t1).compareTo(t2) : comparator;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T e) {
		if (e == null) throw new NullPointerException();
		if (size >= heap.length) {
			grow();
		}
		heap[size] = e;
		size++;
		tamiserVersLeHaut(size - 1);
	}

	private void tamiserVersLeHaut(int k) {
		T v = heap[k];
		while (k > 0 && comparator.compare(heap[(k - 1) / 2], v) > 0) {
			heap[k] = heap[(k - 1) / 2];
			k = (k - 1) / 2;
		}
		heap[k] = v;
	}

	private void tamiserVersLeBas(int k) {
		T v = heap[k];
		while (k < size / 2) {
			int child = 2 * k + 1;
			if (child < size - 1 && comparator.compare(heap[child], heap[child + 1]) > 0) {
				child++;
			}
			if (comparator.compare(v, heap[child]) <= 0) {
				break;
			}
			heap[k] = heap[child];
			k = child;
		}
		heap[k] = v;
	}

	private void grow() {
		heap = Arrays.copyOf(heap, heap.length*2);
	}	

	@Override
	public T peek() {
		if (size > 0) return heap[0];
		return null;
	}

	@Override
	public T poll() {
		if (size > 0) {
			T valueToReturn = heap[0];
			heap[0] = heap[--size];
			if (size > 0) {
				tamiserVersLeBas(0);
			}
			return valueToReturn;
		}
		return null;
	}

}
