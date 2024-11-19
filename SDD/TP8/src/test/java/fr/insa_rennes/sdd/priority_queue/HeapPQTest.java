package fr.insa_rennes.sdd.priority_queue;

import org.junit.jupiter.api.Test;

public class HeapPQTest {
	PriorityQueueTestCommon test = new PriorityQueueTestCommon(() -> new HeapPQ<>());
	
	@Test
	public void testAddPeekPoll() {		
		test.testAddPeekPoll();
	}
	
	@Test
	public void testSize() {
		test.testSize();
	}
	
	@Test
	public void testIsEmpty() {
		test.testIsEmpty();
	}

}
