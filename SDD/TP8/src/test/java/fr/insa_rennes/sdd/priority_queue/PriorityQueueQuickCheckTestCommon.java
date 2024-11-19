package fr.insa_rennes.sdd.priority_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.pholser.junit.quickcheck.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueQuickCheckTestCommon {
	Supplier<PriorityQueue<Integer>> factory;	
	
	PriorityQueueQuickCheckTestCommon(Supplier<PriorityQueue<Integer>> factory) {
		this.factory = factory;
	}
	
	public void sameSize(List<Integer> elements) {
        PriorityQueue<Integer> pq = factory.get();
    	for (Integer i : elements) {
        	pq.add(i);
        }
    	assertEquals(elements.size(), pq.size());
    }

    public void isEmpty(List<Integer> elements, Integer nbPolls) {
    	PriorityQueue<Integer> pq = factory.get();
    	for (Integer i : elements) {
        	pq.add(i);
        }
    	for (int i = 0; i < nbPolls; i++) {
    		pq.poll();
    	}
    	assertEquals(nbPolls >= elements.size() ? true : false, pq.isEmpty());
    }
	
	@Property(trials = 500) 
    public void arrayIsSorted(List<Integer> elements) {
		PriorityQueue<Integer> pq = factory.get();
    	for (Integer i : elements) {
        	pq.add(i);
        }
    	Object[] array = new Integer[pq.size()];
    	int index = 0;
    	while (!pq.isEmpty()) {
    		array[index++] = pq.poll();
    	}
    	elements.sort(Integer::compare);
		for (int i = 0; i < elements.size(); i++) {
			int difference = elements.get(i) - (Integer) array[i];
			if (difference != 0) {
				System.out.println(Arrays.toString(elements.toArray()));
				System.out.println(Arrays.toString(array));
				break;
			}
		}
        assertArrayEquals(elements.toArray(), array);
    }
	
	@Property(trials = 500) 
    public void peekIsMinAndSizeUnchanged(List<Integer> elements) {
		PriorityQueue<Integer> pq = factory.get();
    	int min = Integer.MAX_VALUE;
    	int n = 0;
        for (Integer i : elements) {
        	pq.add(i);
        	n++;
        	min = Math.min(min, i);
        	assertEquals(min, pq.peek());
        	assertEquals(n, pq.size());
        }
    }		
	
	@Property(trials = 500) 
    public void pollIsMinAndDecrementSize(List<Integer> elements) {
        OrderedArrayPQ<Integer> pq = new OrderedArrayPQ<>();
        for (Integer i : elements) {
        	pq.add(i);
        }
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
        	int n = pq.size();
        	l.add(pq.poll());
        	assertEquals(n - 1, pq.size());
        }
        elements.sort(Integer::compare);
        assertTrue(Arrays.equals(elements.toArray(), l.toArray()));
    }
}
