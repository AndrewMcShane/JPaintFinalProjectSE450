package controller;

import model.ShapeList;
import model.interfaces.IShape;

public class CreateShapeCommand implements ICommand {

	ShapeList shapeList;
	IShape selectedShape;
	
	public CreateShapeCommand(ShapeList shapeList, IShape shape) {
		this.shapeList = shapeList;
		this.selectedShape = shape;
	}
	
	@Override
	public void execute() {
		shapeList.addShape(selectedShape);
	}

	@Override
	public boolean undo() {
		if(shapeList != null && selectedShape != null) {
			shapeList.removeShape(selectedShape);
			return true;
		}
		return false;
	}

	@Override
	public boolean redo() {
		if(shapeList != null && selectedShape != null) {
			execute();
			return true;
		}
		return false;
	}

}
