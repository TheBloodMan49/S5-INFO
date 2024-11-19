package fr.insa_rennes.sdd.dijkstra;

public class DijkstraNode<T> implements Comparable<DijkstraNode<T>> {	
	final Double cost;	
	final T vertex;	
	final T prev;

	public DijkstraNode(Double cost, T vertex) {
		this(cost, vertex, null);
	}
	
	public DijkstraNode(Double cost, T vertex, T prev) {
		this.cost = cost;
		this.vertex = vertex;
		this.prev = prev;
	}

	@Override
	public int compareTo(DijkstraNode<T> node) {
		return Double.compare(cost, node.cost);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((prev == null) ? 0 : prev.hashCode());
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		DijkstraNode other = (DijkstraNode) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (prev == null) {
			if (other.prev != null)
				return false;
		} else if (!prev.equals(other.prev))
			return false;
		if (vertex == null) {
            return other.vertex == null;
		} else return vertex.equals(other.vertex);
    }

}
