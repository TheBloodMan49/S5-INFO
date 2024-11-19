package fr.insa_rennes.sdd.priority_queue;

import java.util.Arrays;
import java.util.Comparator;

import fr.insa_rennes.sdd.util.ArraySupport;


public class OrderedArrayPQ<T> implements PriorityQueue<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 8;
	private Comparator<? super T> comparator;
	private int size;
	private T[] array;

	public OrderedArrayPQ() {
		this(DEFAULT_INITIAL_CAPACITY, null);
	}	

	public OrderedArrayPQ(int initialCapacity) {
		this(initialCapacity, null);
	}

	public OrderedArrayPQ(Comparator<? super T> comparator) {
		this(DEFAULT_INITIAL_CAPACITY, comparator);
	}

	@SuppressWarnings("unchecked")
	public OrderedArrayPQ(int initialCapacity, Comparator<? super T> comparator) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		array = (T[])new Object[initialCapacity];
		this.comparator = comparator == null ? (t1, t2) -> ((Comparable<? super T>)t1).compareTo(t2) : comparator;
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
		if (e == null) {
			throw new NullPointerException();
		}
		if (size >= array.length) {
			grow();
		}		
		int index = Arrays.binarySearch(array, 0, size, e, comparator);
		if (index >= 0) {
			insert(e, index);
		} else {
			insert(e, -(index + 1));
		}
		size++;
	}

	private void insert(T e, int index) {
		for (int i = size - 1; i >= index; i--) {
			array[i + 1] = array[i];
		}
		array[index] = e; 
	}
		
	@Override
	public T peek() {		
		return size == 0 ? null : array[0];
	}

	@Override
	public T poll() {
		T res = peek();
		for (int i = 0; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		size = size == 0 ? 0 : size - 1;
		array[size] = null;
		return res;
	}
	
	private void grow() {		        
		int oldLength = array.length;
		array = Arrays.copyOf(array, ArraySupport.newLength(oldLength, oldLength + 1, oldLength << 1));
	}

}
