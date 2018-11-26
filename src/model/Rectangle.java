package model;

import java.awt.Graphics2D;

import java.awt.Color;
import model.interfaces.IShape;
import view.gui.PaintCanvas;

public class Rectangle implements IShape {

	private BoundingBox bounds;
	private ShapeShadingType shapeShading;
	private ShapeColor borderColor;
	private ShapeColor fillColor;
	
	public Rectangle(BoundingBox bounds, ShapeShadingType shapeShading,
			ShapeColor borderColor, ShapeColor fillColor) {
		this.bounds = bounds;
		this.shapeShading = shapeShading;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
	}
	
	@Override
	public BoundingBox getBounds() {
		return bounds;
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}
	
	@Override
	public ShapeShadingType getShadingType() {
		return shapeShading;
	}
	
	@Override
	public ShapeColor getBorderColor() {
		return borderColor;
	}
	
	@Override
	public ShapeColor getFillColor() {
		return fillColor;
	}

	@Override
	public void draw(PaintCanvas canvas) {
		Graphics2D g2d = canvas.getGraphics2D();
		switch(shapeShading) {
			case FILLED_IN : {				
				g2d.setColor(ColorMapper.getInstance().getColor(fillColor));
				g2d.fillRect(bounds.getLeftBound(), bounds.getTopBound(), bounds.getWidth(), bounds.getHeight());
				break;
			}
			case OUTLINE : {
				g2d.setColor(ColorMapper.getInstance().getColor(borderColor));
				g2d.drawRect(bounds.getLeftBound(), bounds.getTopBound(), bounds.getWidth(), bounds.getHeight());
				break;
			}
			case OUTLINE_AND_FILLED_IN : {
				g2d.setColor(ColorMapper.getInstance().getColor(fillColor));
				g2d.fillRect(bounds.getLeftBound(), bounds.getTopBound(), bounds.getWidth(), bounds.getHeight());
				g2d.setColor(ColorMapper.getInstance().getColor(borderColor));
				g2d.drawRect(bounds.getLeftBound(), bounds.getTopBound(), bounds.getWidth(), bounds.getHeight());
				break;
			}
			default : 
				throw new Error("Unrecognized shape shading mode in draw method!");
			
		}
	}
	
	@Override
	public IShape makeClone() {
		return new Rectangle(bounds.makeClone(), shapeShading, borderColor, fillColor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;
		Rectangle s = (Rectangle) obj;
		return s.bounds.equals(bounds) 
				&& s.getShapeType().equals(getShapeType())
				&& s.getShadingType().equals(shapeShading)
				&& s.getBorderColor().equals(borderColor)
				&& s.getFillColor().equals(fillColor);
	}
	
}
