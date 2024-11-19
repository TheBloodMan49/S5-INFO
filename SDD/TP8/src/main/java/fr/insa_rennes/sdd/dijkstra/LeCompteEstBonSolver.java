package fr.insa_rennes.sdd.dijkstra;

import java.util.List;
import java.util.Scanner;
import fr.insa_rennes.sdd.graph.LeCompteEstBonGraph;
import fr.insa_rennes.sdd.graph.LeCompteEstBonGraph.State;

public class LeCompteEstBonSolver {
	public static void main(String[] args) {
		System.out.println("Give me the six plaques");
		int[] plaques = new int[6];
		try (Scanner in = new Scanner(System.in)) {
			for (int i = 0; i < 6; i++) {
				plaques[i] = in.nextInt();
			}
			LeCompteEstBonGraph ceb = new LeCompteEstBonGraph(plaques);		
			Dijkstra<LeCompteEstBonGraph.State> dj = new Dijkstra<LeCompteEstBonGraph.State>(ceb, ceb.initialState());		
			System.out.println("number of vertices: " + ceb.numberOfVertices());
			System.out.println("number of edges: " + ceb.numberOfEdges());
			System.out.println("number of different results: " + ceb.compteToState().size());
			while (true) {
				System.out.println("Compte?");
				int compte = in.nextInt();
				showCompte(ceb, dj, compte);
			}	
		}		
	}

	private static void showCompte(LeCompteEstBonGraph ceb, Dijkstra<State> dj, int compte) {		
		if (!ceb.compteToState().containsKey(compte)) {
			System.out.printf("The compte %d is not possible\n", compte);
		} else {
			List<LeCompteEstBonGraph.State> res = ceb.compteToState().get(compte);
			res.sort((s1, s2) -> Double.compare(dj.getCost(s1), dj.getCost(s2)));
			for (LeCompteEstBonGraph.State s : res.subList(0, Math.min(10, res.size()))) {
				System.out.printf("%s with cost %f\n", s.toString(), dj.getCost(s));
			}
		}
	}
}
