package controller;

import java.util.List;

import model.ShapeList;
import model.interfaces.IShape;

public class DeleteCommand implements ICommand {

	private ShapeList shapeList;
	private List<IShape> selection;
	
	public DeleteCommand(ShapeList shapeList, List<IShape> selection) {
		this.shapeList = shapeList;
		this.selection = selection;
	}
	
	@Override
	public void execute() {
		for(IShape s : selection) {
			shapeList.removeShape(s);
		}
	}

	@Override
	public boolean undo() {
		if(shapeList == null || selection == null) return false;
		for(IShape s : selection) {
			shapeList.addShape(s);
		}
		return true;
	}

	@Override
	public boolean redo() {
		if(shapeList == null || selection == null) return false;
		execute();
		return false;
	}

}
