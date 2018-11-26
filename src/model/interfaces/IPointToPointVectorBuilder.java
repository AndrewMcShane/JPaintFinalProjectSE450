package model.interfaces;

import model.Point;
import model.PointToPointVector;;

public interface IPointToPointVectorBuilder {
	void setStartPoint(Point p);
	void setEndPoint(Point p);
	PointToPointVector buildPointToPointVector();
}
