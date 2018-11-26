package unitTests;

import org.junit.jupiter.api.Test;

import model.Point;
import model.PointToPointVector;
import model.PointToPointVectorBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class PTPVBuilderUnitTest {

	@Test
	public void testBuilder() {
		
		PointToPointVectorBuilder builder;
		Point start;
		Point end;
		PointToPointVector pVector;
		
		start = new Point(1,5);
		end = new Point(6,12);
		pVector = new PointToPointVector(start,end);
		builder = new PointToPointVectorBuilder();
		builder.setStartPoint(start);
		builder.setEndPoint(end);
		
		assertTrue(builder.buildPointToPointVector().toString().equals(pVector.toString()));
		
	}
}
