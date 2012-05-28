package villebasse.gamelogic;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DirectionTest {

	Direction dir;

	@Before
	public void setUp() {
		Direction dir = new Direction(Direction.NORTH);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testEquals() {
		Direction north = new Direction(Direction.NORTH);
		Direction west  = new Direction(Direction.WEST);

		assertTrue(dir.equals(north));
		assertTrue(!dir.equals(west));
	}

	@Test
	public void testIterator() {
		String directions = "";

		for (Direction d : new Direction(Direction.EAST))
			directions += d + " ";

		assertTrue(directions.equals("east south west north "));
	}

	@Test
	public void testNext() {
		assertTrue(dir.next().equals(new Direction(Direction.EAST)));
		dir = dir.next();
		assertTrue(dir.next().equals(new Direction(Direction.SOUTH)));
		dir = dir.next();
		assertTrue(dir.next().equals(new Direction(Direction.WEST)));
		dir = dir.next();
		assertTrue(dir.next().equals(new Direction(Direction.NORTH)));
	}

	@Test
	public void testOrdinal() {
		assertEquals(new Direction(Direction.NORTH).ordinal(), 0);
		assertEquals(new Direction(Direction.EAST).ordinal(), 1);
		assertEquals(new Direction(Direction.SOUTH).ordinal(), 2);
		assertEquals(new Direction(Direction.WEST).ordinal(), 3);
	}

	@Test
	public void testOpposite() {
		assertTrue(new Direction(Direction.NORTH).opposite().equals(new Direction(Direction.SOUTH)));
		assertTrue(new Direction(Direction.SOUTH).opposite().equals(new Direction(Direction.NORTH)));
		assertTrue(new Direction(Direction.EAST).opposite().equals(new Direction(Direction.WEST)));
		assertTrue(new Direction(Direction.WEST).opposite().equals(new Direction(Direction.EAST)));
	}

	@Test
	public void testRotate_Direction() {
	}

	@Test
	public void testRotate_int() {
	}

	@Test
	public void testToString() {
	}
}
