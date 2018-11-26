package model;

import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;

public class ShapeFactory implements IShapeFactory{
	
	private final ApplicationState appState;
	
	public ShapeFactory(ApplicationState appState) {
		this.appState = appState;
	}
	
	public IShape createShape(BoundingBox bounds) {
		switch(appState.getActiveShapeType()){
			case RECTANGLE: {
				return createRectangle(bounds);
			}
			case ELLIPSE: {
				return createEllipse(bounds);
			}
			case TRIANGLE: {
				return createTriangle(bounds);
			}
			default:
				throw new Error("Unknown shape type encountered by the ShapeFactory!");
		}
	}
	
	private IShape createRectangle(BoundingBox bounds) {
		return new Rectangle(bounds, appState.getActiveShapeShadingType(),
				appState.getActivePrimaryColor(), appState.getActiveSecondaryColor());
	}
	
	private IShape createEllipse(BoundingBox bounds) {
		return new Ellipse(bounds, appState.getActiveShapeShadingType(),
				appState.getActivePrimaryColor(), appState.getActiveSecondaryColor());
	}
	
	private IShape createTriangle(BoundingBox bounds) {
		return new Triangle(bounds, appState.getActiveShapeShadingType(),
				appState.getActivePrimaryColor(), appState.getActiveSecondaryColor());
	}
}
