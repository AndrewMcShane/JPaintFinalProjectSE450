package unitTests;

import org.junit.jupiter.api.Test;

import model.BoundingBox;
import model.Point;

import static org.junit.jupiter.api.Assertions.*;

public class BoundingBoxUnitTest {

	@Test
	public void testEnvelopes() {
		
		BoundingBox smallBox;
		BoundingBox largeBox;
		
		smallBox = new BoundingBox(new Point(40,40), new Point(80,80));
		largeBox = new BoundingBox(new Point(20,20), new Point(100,100));
		
		assertTrue(largeBox.envelopes(smallBox));
		assertFalse(smallBox.envelopes(largeBox));
		assertFalse(largeBox.envelopes(largeBox));
	}
	
	@Test
	public void testIsWithin() {
		
		BoundingBox smallBox;
		BoundingBox largeBox;
		
		smallBox = new BoundingBox(new Point(40,40), new Point(80,80));
		largeBox = new BoundingBox(new Point(20,20), new Point(100,100));
		
		assertTrue(smallBox.isWithin(largeBox));
		assertFalse(largeBox.isWithin(smallBox));
		assertFalse(largeBox.isWithin(largeBox));
	}
	
	@Test 
	public void testEquals() {
		
		BoundingBox aBox;
		BoundingBox sameSizeBox;
		
		aBox = new BoundingBox(new Point(40,40), new Point(80,80));
		sameSizeBox = new BoundingBox(new Point(40,40), new Point(80,80));
		
		assertTrue(sameSizeBox.equals(aBox));
	}
}
