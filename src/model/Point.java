package model;

/*
 * Point Class is the base class that represents a single point in space.
 * Because this is a simple paint application, I will be treating the point
 * as a point and not a vector in space (so no fancy normalize and magnitude methods unfortunately).
 */
public class Point {

	private int x;
	private int y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		Point point = (Point) obj;
		return x == point.getX() && y == point.getY();
	}
}
