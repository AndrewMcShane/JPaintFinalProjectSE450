package model.interfaces;

import model.BoundingBox;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.gui.PaintCanvas;

public interface IShape {
	BoundingBox getBounds();
	void draw(PaintCanvas canvas);

	ShapeType getShapeType();
	ShapeShadingType getShadingType();
	ShapeColor getBorderColor();
	ShapeColor getFillColor();
	
	IShape makeClone();
	boolean equals(Object obj);

}
