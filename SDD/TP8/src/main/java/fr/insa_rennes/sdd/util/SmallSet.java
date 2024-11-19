package fr.insa_rennes.sdd.util;

// Elements must be between 0 and 63 inclusive
public class SmallSet {
	private long set;
	
	public boolean contains(int i) {
		return (set & 1L << i) != 0;
	}
	
	public void insert(int i) {
		set |= 1L << i;
	}
	
	public void remove(int i) {
		set = set & ~(1L << i);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (set ^ (set >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		SmallSet other = (SmallSet) obj;
		if (set != other.set)
			return false;
		return true;
	}		
	
}
