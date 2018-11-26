package model;

import java.awt.Graphics2D;
import java.awt.Polygon;

import model.interfaces.IShape;
import view.gui.PaintCanvas;

public class Triangle implements IShape {

	private BoundingBox bounds;
	private ShapeShadingType shapeShading;
	private ShapeColor borderColor;
	private ShapeColor fillColor;
	
	public Triangle(BoundingBox bounds, ShapeShadingType shapeShading,
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
		return ShapeType.TRIANGLE;
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
		/*
		 * note on logic for triangle calculations:
		 * triangle will be 3 coordinates: (Ax, By), (Bx,By), (Cx, Ay) where Cx = 1/2(Bx-Ay)
		 * Using Polygon , order in Ax, Bx, Cx : Ay, By, Cy.
		 */
		Graphics2D g2d = canvas.getGraphics2D();
		Point C = new Point(bounds.getMidpoint().getX(), bounds.getTopBound());
		Polygon triangle = new Polygon(
				new int[] { bounds.getLeftBound(), C.getX(), bounds.getRightBound() },
				new int[] { bounds.getBottomBound(), C.getY(), bounds.getBottomBound() }, 
				3
				);
		
		switch(shapeShading) {
			case FILLED_IN : {				
				g2d.setColor(ColorMapper.getInstance().getColor(fillColor));
				g2d.fillPolygon(triangle);
				break;
			}
			case OUTLINE : {
				g2d.setColor(ColorMapper.getInstance().getColor(borderColor));
				g2d.drawPolygon(triangle);
				break;
			}
			case OUTLINE_AND_FILLED_IN : {
				g2d.setColor(ColorMapper.getInstance().getColor(fillColor));
				g2d.fillPolygon(triangle);
				g2d.setColor(ColorMapper.getInstance().getColor(borderColor));
				g2d.drawPolygon(triangle);
				break;
			}
			default : 
				throw new Error("Unrecognized shape shading mode in draw method!");
			
		}
	}
	
	@Override
	public IShape makeClone() {
		return new Triangle(bounds.makeClone(), shapeShading, borderColor, fillColor);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;
		Triangle s = (Triangle) obj;
		return s.bounds.equals(bounds) 
				&& s.getShapeType().equals(getShapeType())
				&& s.getShadingType().equals(shapeShading)
				&& s.getBorderColor().equals(borderColor)
				&& s.getFillColor().equals(fillColor);
	}




}
