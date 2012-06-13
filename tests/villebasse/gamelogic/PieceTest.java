package villebasse.gamelogic;

import villebasse.gamelogic.defaultpieces.*;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;


public class PieceTest
{

	Piece piece;
	Meeple meeple = new Meeple(new Player("mrs. foo", Color.pink));

	@Before
	public void setUp()
	{
		piece = new PieceCityCorner();
	}

	@After
	public void tearDown()
	{
	}

	@Test
	public void testGetRotation()
	{
		assertTrue(piece.getRotation().equals(new Direction(Direction.NORTH)));
		piece.rotate();
		assertTrue(piece.getRotation().equals(new Direction(Direction.EAST)));
		piece.rotate();
		assertTrue(piece.getRotation().equals(new Direction(Direction.SOUTH)));
		piece.rotate();
		assertTrue(piece.getRotation().equals(new Direction(Direction.WEST)));
	}

	@Test
	public void testEdge()
	{
		assertEquals(
			piece.edge(new Direction(Direction.NORTH)), Piece.Terrain.FIELD);
		assertEquals(
			piece.edge(new Direction(Direction.EAST)),  Piece.Terrain.CITY);
		assertEquals(
			piece.edge(new Direction(Direction.SOUTH)), Piece.Terrain.CITY);
		assertEquals(
			piece.edge(new Direction(Direction.WEST)),  Piece.Terrain.FIELD);
	}

	@Test
	public void testEdges()
	{
		Piece.Terrain[] edges = new Piece.Terrain[]{
			Piece.Terrain.FIELD,
			Piece.Terrain.CITY,
			Piece.Terrain.CITY,
			Piece.Terrain.FIELD,
		};
		assertTrue(Arrays.equals(piece.edges(), edges));
	}

	@Test
	public void testRotate()
	{
		piece.rotate();
		Piece.Terrain[] edges = new Piece.Terrain[]{
			Piece.Terrain.FIELD,
			Piece.Terrain.FIELD,
			Piece.Terrain.CITY,
			Piece.Terrain.CITY,
		};
		assertTrue(Arrays.equals(piece.edges(), edges));
	}

	@Test
	public void testRotate_Int()
	{
		piece.rotate(2);
		Piece.Terrain[] edges = new Piece.Terrain[]{
			Piece.Terrain.CITY,
			Piece.Terrain.FIELD,
			Piece.Terrain.FIELD,
			Piece.Terrain.CITY,
		};
		assertTrue(Arrays.equals(piece.edges(), edges));
	}

	@Test
	public void testPlaceMeeple()
	{
		assertFalse(piece.placeMeeple(0.5, 0.5, null));
		assertTrue(piece.placeMeeple(0.5, 0.5, meeple));
		assertFalse(piece.placeMeeple(0.5, 0.5, meeple));
	}

	@Test
	public void testRemoveMeeple()
	{
		assertFalse(piece.hasMeeple());
		assertTrue(piece.placeMeeple(0.5, 0.5, meeple));
		assertTrue(piece.hasMeeple());

		assertEquals(piece.removeMeeple(), meeple);
		assertNull(piece.removeMeeple());
	}
}
