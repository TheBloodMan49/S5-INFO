package fr.insa_rennes.sdd.priority_queue;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class AllPQQuickCheckTest {
	List<PriorityQueue<Integer>> all = List.of(new HeapPQ<>(), new OrderedArrayPQ<>());
	
	@Property(trials = 1000)
    public void arraySameSize(List<Integer> elements) {
		java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>();
		for (int e : elements) {
			pq.add(e);
			all.forEach(p -> p.add(e));
			all.forEach(p -> assertEquals(pq.peek(), p.peek()));
			all.forEach(p -> assertEquals(pq.size(), p.size()));
			all.forEach(p -> assertEquals(pq.isEmpty(), p.isEmpty()));
		}
		for (int i = 0; i < elements.size(); i++) {
			Integer v = pq.poll();			
			all.forEach(p -> assertEquals(v, p.poll()));
			all.forEach(p -> assertEquals(pq.size(), p.size()));
			all.forEach(p -> assertEquals(pq.isEmpty(), p.isEmpty()));
		}
		all.forEach(p -> assertEquals(pq.peek(), p.peek()));
		Integer v = pq.poll();
		all.forEach(p -> assertEquals(v, p.poll()));
		all.forEach(p -> assertEquals(pq.isEmpty(), p.isEmpty()));
    }
}
