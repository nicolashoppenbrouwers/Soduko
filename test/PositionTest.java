package test;

import static org.junit.Assert.*;
import org.junit.Test;
import worms.model.Position;


public class PositionTest {

	@Test
	public void test_position_TrueCase() {
		Position position = new Position(2,3);
		assertEquals(position.getX(), 2, 0);
		assertEquals(position.getY(), 3, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_position_FalseCase_illegalY() {
		Position position = new Position(7, Double.NaN);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_position_FalseCase_IllegalX() {
		Position position = new Position(Double.NaN, 3);
	}

	
}
