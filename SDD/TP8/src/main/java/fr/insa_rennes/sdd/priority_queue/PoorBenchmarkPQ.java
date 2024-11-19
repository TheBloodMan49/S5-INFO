package fr.insa_rennes.sdd.priority_queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class PoorBenchmarkPQ {
	private static final int SIZE = 35000;
	private static final int NB_SAMPLING = 20;
	private static Integer[] testArray;
	
	static {	
		testArray = new Integer[SIZE];
		Arrays.setAll(testArray, i -> i);		
		List<Integer> l = Arrays.asList(testArray);
		Collections.shuffle(l);		
	}
	
	private static Double run(int n, Supplier<PriorityQueue<Integer>> create) {
		if (n == 0) {
			throw new IllegalArgumentException();
		}
		long[] res = new long[n + 2];
		Arrays.parallelSetAll(res, (int index) -> {
			long start = System.nanoTime();
			PriorityQueue<Integer> pq = create.get();
			Arrays.stream(testArray).forEach(pq::add);
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				pq.poll();
			}
			long stop = System.nanoTime();
			return stop - start;
		});
		Arrays.parallelSort(res); 
		return Arrays.stream(res, 1, res.length - 1).average().getAsDouble();
	}
	
	public static void main(String[] args) {		
		System.out.printf("Average time for HeapPQ: %f ms\n", run(NB_SAMPLING, () -> new HeapPQ<Integer>(SIZE)) / 1000000);
		System.out.printf("Average time for OrderedPQ: %f ms\n", run(NB_SAMPLING, () -> new OrderedArrayPQ<Integer>(SIZE)) / 1000000);
	}
}
