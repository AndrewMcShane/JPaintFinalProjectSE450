package controller;

import model.ShapeClipboard;
import model.ShapeList;

public class CopyCommand implements ICommand {

	private ShapeClipboard clipboard;
	private ShapeList shapeList;
	
	public CopyCommand(ShapeList shapeList, ShapeClipboard clipboard) {
		this.shapeList = shapeList;
		this.clipboard = clipboard;
	}
	
	@Override
	public void execute() {
		clipboard.copyToClipboard(shapeList.getSelection());
	}

	@Override
	public boolean undo() {
		//these commands are not implemented for CopyCommand;
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean redo() {
		//these commands are not implemented for CopyCommand;
		throw new UnsupportedOperationException();
	}

}
