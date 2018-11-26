package model;

import java.util.ArrayList;
import java.util.List;

import controller.IObservable;
import controller.IObserver;
import model.interfaces.IShape;

public class ShapeList implements IObservable {

	private final List<IShape> shapes;
	private List<IShape> selection;
	private List<IObserver> observers;
	
	public ShapeList() {
		shapes = new ArrayList<IShape>();
		selection = new ArrayList<IShape>();
		observers = new ArrayList<IObserver>();
	}
	
	public boolean containsShape(IShape shape) {
		return shapes.contains(shape);
	}
	
	public void removeShape(IShape shape) {
		for(int i=0; i< shapes.size(); i++) {
			if(shapes.get(i).equals(shape)) {
				shapes.remove(i);
				break;
			}
		}
		clearSelection();
		setChanges();
	}
	
	public void removeShapes(List<IShape> shapes) {
		clearSelection();
		for(IShape s : shapes) {
			for(int i=0; i< this.shapes.size(); i++) {
				if(this.shapes.get(i).equals(s)) {
					this.shapes.remove(i);
					break;
				}
			}
		}
		setChanges();
	}
	
	public void addShape(IShape shape) {
		shapes.add(shape);
		clearSelection();
		setChanges();
	}
	
	//mainly so if many shapes are being added that the frame does unnecessarily redraw.
	public void addShapes(List<IShape> shapes) {
		clearSelection();
		for(IShape s : shapes) {
			this.shapes.add(s);
		}
		setChanges();
	}
	
	public List<IShape> getShapes(){
		return shapes;
	}

	public List<IShape> getSelection(){
		return selection;
	}
	
	public void clearSelection() {
		selection = new ArrayList<IShape>();
	}
	
	public void selectObjects(BoundingBox bounds) {
		clearSelection();
		for(IShape s : shapes) {
			if(bounds.envelopes(s.getBounds())) {
				selection.add(s);
			}
		}
	}
	
	// This feels sloppy, but its a helper method to call into the observer pattern from some commands.
	public void setChanges() {
		notifyObservers();
	}
	
	
	/*----Observer methods----*/
	

	@Override
	public void attach(IObserver o) {
		observers.add(o);
	}

	@Override
	public void detach(IObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(IObserver o : observers) {
			o.update(this);
		}
	}

	
	
	
}
