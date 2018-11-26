package model;

import model.interfaces.IPointToPointVectorBuilder;


public class PointToPointVectorBuilder implements IPointToPointVectorBuilder {

	private Point startPoint;
	private Point endPoint;
	
	public PointToPointVectorBuilder() {
		startPoint = new Point(0,0);
		endPoint = new Point(0,0);
	}
	
	@Override
	public void setStartPoint(Point p) {
		this.startPoint = p;
	}

	@Override
	public void setEndPoint(Point p) {
		this.endPoint = p;
	}

	@Override
	public PointToPointVector buildPointToPointVector() {
		return new PointToPointVector(startPoint, endPoint);
	}

}
