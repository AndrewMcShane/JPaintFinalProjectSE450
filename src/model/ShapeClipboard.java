package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.IShape;

public class ShapeClipboard {

	private List<IShape> copiedShapes;
	private int numPastes;
	
	public ShapeClipboard() {
		copiedShapes = new ArrayList<IShape>();
		numPastes = 0;
	}
	
	public void copyToClipboard(List<IShape> shapes) {
		// deep copy here: create new shapes!
		numPastes = 0;
		copiedShapes.clear();
		for(IShape s: shapes) {
			IShape clone = s.makeClone();
			copiedShapes.add(clone);
		}
	}
	
	public List<IShape> getClipboardContents(){
		//send back a copy of the copied shapes
		List<IShape> shapesCopy = new ArrayList<IShape>();
		for (IShape s: copiedShapes) {
			shapesCopy.add(s.makeClone());
		}
		return shapesCopy;
	}
	
	public int getNumPastes() {
		return numPastes;
	}
	
	public void incrememntPastes() {
		numPastes++;
	}
	
	//helpful for undo command.
	public void decrementPastes() {
		numPastes--;
	}
}
