package controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.PointToPointVectorBuilder;
import model.Point;
import model.PointToPointVector;
import model.interfaces.IPointToPointVectorBuilder;


public class MouseHandler extends MouseAdapter implements IObservable {

	private List<IObserver> observers = new ArrayList<IObserver>();
	private IPointToPointVectorBuilder builder;
	
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
		PointToPointVector vector = builder.buildPointToPointVector();
		for(IObserver o : observers) {
			o.update(vector);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		builder = new PointToPointVectorBuilder();
		builder.setStartPoint(
				new Point(e.getX(), e.getY())
				);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		builder.setEndPoint(
				new Point(e.getX(), e.getY())
				);
		notifyObservers();
	}

	

}
