package model.interfaces;

import model.BoundingBox;

public interface IShapeFactory {
	IShape createShape(BoundingBox bounds);
}
