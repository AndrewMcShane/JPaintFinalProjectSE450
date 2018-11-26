package model;

public class PointToPointVector {

	/*
	 * It should be differentiated between a BoundingBox and PointToPointVector:
	 * 
	 * PointToPointVector CAN:
	 * - Have negative component vectors
	 * 
	 * BoundingBox CANT:
	 * - Have negative width and height. 
	 * 
	 * PointToPoint DOES:
	 * - allow an easy construction of a BoundingBox if needed.
	 * - solve the issue of MouseHandler having a release that is in the negative direction of the press data point. 
	 */
	
	private Point startPoint;
	private Point endPoint;

	
	public PointToPointVector(Point start, Point end) {
		this.startPoint = start;
		this.endPoint = end;
		
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	//returns the x-component of the vector: it is important to differentiate this from width: a width cannot be negative, x-component of a vector can!
	public int getXComponent() {
		return endPoint.getX() - startPoint.getX();
	}
	
	public int getYComponent() {
		return endPoint.getY() - startPoint.getY();
	}
	
	//returns a Point that has the data about its components condensed into a single point vector in local space relative to the start point.
	public Point getComponentPoint() {
		return new Point(getXComponent(), getYComponent());
	}
	
	//get the bounding box of this vector.
	public BoundingBox getBounds() {
		return new BoundingBox(startPoint, endPoint);
	}
	
	
	@Override
	public String toString() {
		return startPoint.toString() + "," + endPoint.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;
		PointToPointVector vector = (PointToPointVector) obj;
		return vector.getStartPoint().equals(startPoint) && vector.getEndPoint().equals(endPoint);
	}
	
}
