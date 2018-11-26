package unitTests;

import org.junit.jupiter.api.Test;

import model.BoundingBox;
import model.Point;
import model.Rectangle;
import model.ShapeColor;
import model.ShapeList;
import model.ShapeShadingType;
import model.interfaces.IShape;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ShapeListUnitTest {

	@Test
	public void testShapeListAdd() {
		ShapeList shapeList;
		List<IShape> shapes;
		
		shapeList = new ShapeList();
		shapes = new ArrayList<IShape>();
		for(int i = 0; i < 10; i++) {
			Rectangle rect = new Rectangle(
					new BoundingBox(new Point(0,0), new Point(10,10)),
					ShapeShadingType.FILLED_IN,
					ShapeColor.GRAY,
					ShapeColor.BLUE
					);
			shapes.add(rect);
			shapeList.addShape(rect);
		}
		
		
		assertEquals(shapeList.getShapes().size(), shapes.size());
		for(IShape s: shapes) {
			assertTrue(shapeList.containsShape(s));
		}
		
		
	}
	
	@Test
	public void testShapeListRemove() {
		ShapeList shapeList;
		List<IShape> shapes;
		
		shapeList = new ShapeList();
		shapes = new ArrayList<IShape>();
		for(int i = 0; i < 10; i++) {
			Rectangle rect = new Rectangle(
					new BoundingBox(new Point(0,0), new Point(10,10)),
					ShapeShadingType.FILLED_IN,
					ShapeColor.GRAY,
					ShapeColor.BLUE
					);
			shapes.add(rect);
			shapeList.addShape(rect);
		}
		
		for(IShape s: shapes) {
			shapeList.removeShape(s);
		}
		
		assertEquals(0, shapeList.getShapes().size());
		
	}

	
	
	
}
