package fr.insa_rennes.sdd.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import fr.insa_rennes.sdd.graph.LeCompteEstBonGraph.State;

class LeCompteEstBonGraphTest {
	
	@Test
	void testCompte() {		
		LeCompteEstBonGraph ceb = new LeCompteEstBonGraph(1, 2, 7, 9, 25, 100);
		LeCompteEstBonGraph.State s = ceb.new State(0, 0);
		long stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		assertEquals(9, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, 5);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);		
		assertEquals(900, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, 5);		
		assertEquals(State.UNFINISHED, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		assertEquals(State.IMPOSSIBLE, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);		
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		assertEquals(State.IMPOSSIBLE, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 1);
		stack = s.push(stack, 0);		
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		assertEquals(1, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);		
		stack = s.push(stack, LeCompteEstBonGraph.DIV);
		assertEquals(4, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 2);		
		stack = s.push(stack, LeCompteEstBonGraph.DIV);
		assertEquals(State.IMPOSSIBLE, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 0);
		stack = s.push(stack, 1);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);		
		assertEquals(2509, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 0);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 1);
		stack = s.push(stack, LeCompteEstBonGraph.DIV);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, 3);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);		
		assertEquals(268, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 0);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 1);
		stack = s.push(stack, LeCompteEstBonGraph.DIV);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, 3);		
		assertEquals(State.UNFINISHED, s.compte(stack));
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 0);
		stack = s.push(stack, LeCompteEstBonGraph.MINUS);
		stack = s.push(stack, 1);
		stack = s.push(stack, LeCompteEstBonGraph.DIV);
		stack = s.push(stack, 2);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		stack = s.push(stack, LeCompteEstBonGraph.TIMES);
		assertEquals(State.IMPOSSIBLE, s.compte(stack));
	}

	@Test
	void testneighbors() {		
		LeCompteEstBonGraph ceb = new LeCompteEstBonGraph(1, 3, 7, 10, 25, 100);
		LeCompteEstBonGraph.State s = ceb.new State(0, 0);
		long stack = 0;
		stack = s.push(stack, 3);
		stack = s.push(stack, 4);
		stack = s.push(stack, 1);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		s = ceb.new State(stack, State.UNFINISHED);
		HashSet<VertexAndWeight<State>> neighbors = s.neighbors(); 
		assertEquals(5, neighbors.size());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 38).count());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 280).count());
		assertEquals(3, neighbors.stream().filter(vw -> vw.vertex.getCompte() == State.UNFINISHED).count());
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, 4);
		s = ceb.new State(stack, State.UNFINISHED);
		neighbors = s.neighbors(); 
		assertEquals(8, neighbors.size());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 75).count());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 4).count());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 2500).count());
		assertEquals(1, neighbors.stream().filter(vw -> vw.vertex.getCompte() == 125).count());
		assertEquals(4, neighbors.stream().filter(vw -> vw.vertex.getCompte() == State.UNFINISHED).count());
		
		stack = 0;
		stack = s.push(stack, 5);
		stack = s.push(stack, LeCompteEstBonGraph.ADD);
		s = ceb.new State(stack, State.IMPOSSIBLE);
		neighbors = s.neighbors(); 
		assertEquals(0, neighbors.size());		
	}
}
