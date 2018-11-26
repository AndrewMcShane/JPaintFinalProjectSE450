package controller;

import model.BoundingBox;
import model.PointToPointVector;
import model.ShapeList;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;

public class MouseHandlerFacade implements IObserver {
	
	private ApplicationState appState;
	private IShapeFactory shapeFactory;
	private ShapeList shapeList;
	
	public MouseHandlerFacade(ApplicationState appState, IShapeFactory shapeFactory, ShapeList shapeList) {
		this.appState = appState;
		this.shapeFactory = shapeFactory;
		this.shapeList = shapeList;
	}
	
	@Override
	public void update(Object arg) {
		if(arg instanceof PointToPointVector) {
			PointToPointVector vector = (PointToPointVector) arg;
			delegate(vector);
		}
	}
	
	private void delegate(PointToPointVector vector) {
		//choose here what to do with the data you are given.
		
		switch(appState.getActiveStartAndEndPointMode()) {
		
			case DRAW: {
				// Represents the bounds of the click. 
				BoundingBox bounds = new BoundingBox(vector.getStartPoint(), vector.getEndPoint());
				// Create the shape data
				IShape shape = shapeFactory.createShape(bounds);
				// Create the command which will draw and add the shape to the shape list
				ICommand cmd = new CreateShapeCommand(shapeList, shape);
				// execute the command
				cmd.execute();
				//add that command to the history.
				CommandHistory.add(cmd);
				break;
			}	
			case SELECT: {
				// Represents the area that the User will select over.
				BoundingBox bounds = new BoundingBox(vector.getStartPoint(), vector.getEndPoint());
				// Set the selection in the shape list
				shapeList.selectObjects(bounds);
				break;
			}
			case MOVE: {
				// Does not need a bounding box because it will utilize the point to point vector.
				// Create the command which will get the selection and perform a translation on the data.
				ICommand cmd = new MoveSelectionCommand(shapeList, vector);
				// execute the command
				cmd.execute();
				// add that command to the history
				CommandHistory.add(cmd);
				break;
			}
			default:
				/*
				 * If you hit this case, there are probably bigger issues. 
				 */
				throw new Error("Invalid application state");
		}
	}
	
	
	
	
}
