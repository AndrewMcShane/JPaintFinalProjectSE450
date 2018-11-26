package view;

import java.awt.Color;
import java.awt.Graphics2D;

import controller.IObserver;
import model.ColorMapper;
import model.ShapeColor;
import model.ShapeList;
import model.interfaces.IShape;
import view.gui.PaintCanvas;


public class DrawShapeHandler implements IObserver{

	private final PaintCanvas canvas;
	
	public DrawShapeHandler(PaintCanvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void update(Object obj) {
		if(obj instanceof ShapeList) {
			//clear the canvas before the repaint. Not the optimal solution, but I couldn't get any luck with repaint() as it would remove everything and redraw nothing.
			Graphics2D g = canvas.getGraphics2D();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
			ShapeList shapeList = (ShapeList) obj;
			for(IShape shape : shapeList.getShapes()) {
				shape.draw(canvas);
			}
			
			
		}
	}
}
