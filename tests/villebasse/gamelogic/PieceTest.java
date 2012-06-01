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


public class PieceTest {

	Piece piece;

	@Before
	public void setUp() {
		piece = new PieceCityCorner();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testEdge() {
		assertEquals(piece.edge(new Direction(Direction.NORTH)), Piece.Terrain.FIELD);
		assertEquals(piece.edge(new Direction(Direction.EAST)),  Piece.Terrain.CITY);
		assertEquals(piece.edge(new Direction(Direction.SOUTH)), Piece.Terrain.CITY);
		assertEquals(piece.edge(new Direction(Direction.WEST)),  Piece.Terrain.FIELD);
	}

	@Test
	public void testEdges() {
		Piece.Terrain[] edges = new Piece.Terrain[]{
			Piece.Terrain.FIELD,
			Piece.Terrain.CITY,
			Piece.Terrain.CITY,
			Piece.Terrain.FIELD,
		};
		assertTrue(Arrays.equals(piece.edges(), edges));
	}

	@Test
	public void testRotate() {
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
	public void testRotate_Int() {
		piece.rotate(2);
		Piece.Terrain[] edges = new Piece.Terrain[]{
			Piece.Terrain.CITY,
			Piece.Terrain.FIELD,
			Piece.Terrain.FIELD,
			Piece.Terrain.CITY,
		};
		assertTrue(Arrays.equals(piece.edges(), edges));
	}
}
