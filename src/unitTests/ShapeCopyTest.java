package unitTests;
import org.junit.jupiter.api.Test;

import model.BoundingBox;
import model.Ellipse;
import model.Point;
import model.Rectangle;
import model.ShapeColor;
import model.ShapeShadingType;
import model.Triangle;
import model.interfaces.IShape;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeCopyTest {
	
	@Test
	public void testRectCopy() {
		
		IShape rect; 
		IShape clone;
		
		rect = new Rectangle(
				new BoundingBox(new Point(10,10), new Point(100,100)),
				ShapeShadingType.FILLED_IN,
				ShapeColor.BLACK,
				ShapeColor.BLUE
				);
		clone = rect.makeClone();
		
		assertFalse(clone == rect);
		assertEquals(clone, rect);
		assertFalse(clone.getBounds() == rect.getBounds());
		assertEquals(clone.getBounds(), rect.getBounds());
	}
	
	@Test
	public void testEllipseCopy() {
		
		IShape ellipse; 
		IShape clone;
		
		ellipse = new Ellipse(
				new BoundingBox(new Point(10,10), new Point(100,100)),
				ShapeShadingType.FILLED_IN,
				ShapeColor.BLACK,
				ShapeColor.BLUE
				);
		clone = ellipse.makeClone();
		
		assertFalse(clone == ellipse);
		assertEquals(clone, ellipse);
		assertFalse(clone.getBounds() == ellipse.getBounds());
		assertEquals(clone.getBounds(), ellipse.getBounds());
	}
	
	@Test
	public void testTriangleCopy() {
		
		IShape triangle; 
		IShape clone;
		
		triangle = new Triangle(
				new BoundingBox(new Point(10,10), new Point(100,100)),
				ShapeShadingType.FILLED_IN,
				ShapeColor.BLACK,
				ShapeColor.BLUE
				);
		clone = triangle.makeClone();
		
		assertFalse(clone == triangle);
		assertEquals(clone, triangle);
		assertFalse(clone.getBounds() == triangle.getBounds());
		assertEquals(clone.getBounds(), triangle.getBounds());
	}
	
}
