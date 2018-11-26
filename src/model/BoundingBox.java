package model;

public class BoundingBox {

	private Point topLeftPoint;
	private Point bottomRightPoint;
	
	public BoundingBox(Point topLeftPoint, Point bottomRightPoint) {	
		
		Point tl = new Point(topLeftPoint.getX(), topLeftPoint.getY());
		Point br = new Point(bottomRightPoint.getX(), bottomRightPoint.getY());
		
		//checks for negative vectors input to correctly set bounds on PointToPointVectors.
		if(topLeftPoint.getX() > bottomRightPoint.getX()) {
			tl.setX(bottomRightPoint.getX());
			br.setX(topLeftPoint.getX());
		}
		if(topLeftPoint.getY() > bottomRightPoint.getY()) {
			tl.setY(bottomRightPoint.getY());
			br.setY(topLeftPoint.getY());
		}
		
		this.topLeftPoint = tl;
		this.bottomRightPoint = br;
			
	}
		
	public Point getMidpoint() {		
		return new Point (
				topLeftPoint.getX() + (getWidth() / 2),
				topLeftPoint.getY() - (getHeight() / 2)
				);			
	}
	
	public int getWidth() {
		return bottomRightPoint.getX() - topLeftPoint.getX();
	}
	
	//Origin is in top left corner! 
	public int getHeight() {
		return bottomRightPoint.getY() - topLeftPoint.getY();
	}
	
	public int getLeftBound() {
		return topLeftPoint.getX();
	}
	
	public int getRightBound() {
		return bottomRightPoint.getX();
	}
	
	public int getTopBound() {
		return topLeftPoint.getY();
	}
	
	public int getBottomBound() {
		return bottomRightPoint.getY();
	}
	
	// Point is additive : to translate left and down, point must have negative x and y values.
	public void translate(Point translation) {
		topLeftPoint.setX(topLeftPoint.getX() + translation.getX());
		//Be mindful of the top left being origin!
		topLeftPoint.setY(topLeftPoint.getY() + translation.getY());
		
		bottomRightPoint.setX(bottomRightPoint.getX() + translation.getX());
		//top left is origin!
		bottomRightPoint.setY(bottomRightPoint.getY() + translation.getY());	
	}
	
	//Does this bounding box surround BoundingBox bounds.
	public boolean envelopes(BoundingBox bounds) {
		return getLeftBound() < bounds.getLeftBound() 
				&& getRightBound() > bounds.getRightBound() 
				&& getTopBound() < bounds.getTopBound() 
				&& getBottomBound() > bounds.getBottomBound();
	}
	
	//Does this bounding box lie within BoundingBox bounds.
	public boolean isWithin(BoundingBox bounds) {
		return getLeftBound() > bounds.getLeftBound() 
				&& getRightBound() < bounds.getRightBound() 
				&& getTopBound() > bounds.getTopBound() 
				&& getBottomBound() < bounds.getBottomBound();
	}
	
	public BoundingBox makeClone() {
		return new BoundingBox(
				new Point(topLeftPoint.getX(), topLeftPoint.getY()),
				new Point(bottomRightPoint.getX(), bottomRightPoint.getY())
				);
				
	}
	
	@Override
	public String toString() {
		return topLeftPoint.toString() + "," + bottomRightPoint.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;
		BoundingBox bounds = (BoundingBox) obj;
		return getLeftBound() == bounds.getLeftBound() 
				&& getRightBound() == bounds.getRightBound() 
				&& getTopBound() == bounds.getTopBound() 
				&& getBottomBound() == bounds.getBottomBound();
		
	}
	

}
