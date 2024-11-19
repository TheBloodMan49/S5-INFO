package fr.insa_rennes.sdd.graph;

public class Coordinate implements Comparable<Coordinate> {	
	public final int row;
	public final int col;
	
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
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
		Coordinate other = (Coordinate) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
	
	@Override
	public int compareTo(Coordinate c) {
		if (row < c.row) return -1;
		if (row > c.row) return 1;
		if (col < c.col) return -1;
		if (col > c.col) return 1;
		return 0;
	}
	
	public static final Left LEFT = Left.instance();
	public static final Top TOP = Top.instance();
	public static final Bottom BOTTOM = Bottom.instance();
	public static final Right RIGHT = Right.instance();
	
	public static class Left extends Coordinate {
		private static Left left = new Left();
		private Left() {
			super(-1, 0);
		}
		public static Left instance() {
			return left;
		}
		@Override
		public String toString() {
			return "Coordinate [Left]";
		}
	}

	public static class Top extends Coordinate {
		private static Top top = new Top();
		private Top() {
			super(-2, 0);
		}
		public static Top instance() {
			return top;
		}
		@Override
		public String toString() {
			return "Coordinate [Top]";
		}
	}

	public static class Right extends Coordinate {
		private static Right right = new Right();
		private Right() {
			super(-3, 0);
		}
		public static Right instance() {
			return right;
		}
		@Override
		public String toString() {
			return "Coordinate [Right]";
		}
	}

	public static class Bottom extends Coordinate {
		private static Bottom bottom = new Bottom();
		private Bottom() {
			super(-4, 0);
		}
		public static Bottom instance() {
			return bottom;
		}
		@Override
		public String toString() {
			return "Coordinate [Bottom]";
		}
	}
	
}
