package controller;

import java.util.ArrayList;
import java.util.List;

import model.Point;
import model.ShapeClipboard;
import model.ShapeList;
import model.interfaces.IShape;

public class PasteCommand implements ICommand {

	private List<IShape> copiedShapes;
	private ShapeList shapeList;
	private ShapeClipboard clipboard;
	
	public PasteCommand(ShapeList shapeList, ShapeClipboard clipboard) {
		this.shapeList = shapeList;
		this.copiedShapes = clipboard.getClipboardContents();
		this.clipboard = clipboard;
	}
	
	
	@Override
	public void execute() {
		//Remember to increment the number of pastes!
		// also send a copied contents! 
		clipboard.incrememntPastes();
		Point offset = new Point(20 * clipboard.getNumPastes() * copiedShapes.size(), 20 * clipboard.getNumPastes() * copiedShapes.size());
		List<IShape> pasteShapes = new ArrayList<IShape>();
		for(IShape s: copiedShapes) {
			s.getBounds().translate(offset);
			IShape clone = s.makeClone();
			pasteShapes.add(clone);
		}
		shapeList.addShapes(pasteShapes);
	}

	@Override
	public boolean undo() {

		clipboard.decrementPastes();
		//doesn't need fancy looping like paste because paste only copies again to prevent operations from changing the shapes.
		shapeList.removeShapes(copiedShapes);
		return false;
	}

	@Override
	public boolean redo() {
		if(copiedShapes == null || shapeList == null || clipboard == null) return false;
		
		List<IShape> pasteShapes = new ArrayList<IShape>();
		for(IShape s: copiedShapes) {
			IShape clone = s.makeClone();
			pasteShapes.add(clone);
		}
		shapeList.addShapes(pasteShapes);
		
		return true;
	}

}
