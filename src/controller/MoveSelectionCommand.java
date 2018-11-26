package controller;

import model.Point;
import model.PointToPointVector;
import model.ShapeList;
import model.interfaces.IShape;

public class MoveSelectionCommand implements ICommand {

	ShapeList shapeList;
	PointToPointVector vector;
	
	public MoveSelectionCommand(ShapeList shapeList, PointToPointVector vector ) {
		this.vector = vector;
		this.shapeList = shapeList;
	}
	
	@Override
	public void execute() {
		for(IShape s : shapeList.getSelection()) {
			s.getBounds().translate(vector.getComponentPoint());
		}
		shapeList.setChanges();
	}

	@Override
	public boolean undo() {
		if(shapeList == null || vector == null) return false;
		// Create a point with an opposite vector direction to undo the translate.
		Point reversePoint = new Point(
				-vector.getComponentPoint().getX(),
				-vector.getComponentPoint().getY()
				);
		for(IShape s : shapeList.getSelection()) {
			s.getBounds().translate(reversePoint);
		}
		shapeList.setChanges();
		return true;
	}

	@Override
	public boolean redo() {
		if(shapeList == null || vector == null) return false;
		execute();
		return true;
	}

}
