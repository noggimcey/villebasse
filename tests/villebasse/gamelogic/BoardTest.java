package villebasse.gamelogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import villebasse.gamelogic.*;
import villebasse.gamelogic.defaultpieces.*;

public class BoardTest {

	Board board;

	@Before
	public void setUp()
	{
		board = new Board(new PieceCityPipe());
	}

	/**
	 * Test of putPieceAbsolute method, of class Board.
	 */
	@Test
	public void testPutPieceAbsolute1()
	{
		try {
			board.putPieceAbsolute(0, 0, new PieceBigCity());
			fail("Absolute point (0,0) is always taken.");
		} catch (Exception e) {
			// goood
		}
	}

	@Test
	public void testPutPieceAbsolute2()
	{
		try {
			board.putPieceAbsolute(2, 0, new PieceBigCity());
			fail("Absolute point (2,0) should be unreachable.");
		} catch (Exception e) {
			// goood
		}
	}

	@Test
	public void testPutPieceAbsolute3()
	{
		for (int i = 1; i <= 10; ++i)
			try {
				board.putPieceAbsolute(i, 0, new PieceCityPipe());
			} catch (Exception e) {
				fail("Absolute point ("+i+",0) should be reachable.");
			}
	}

	@Test
	public void testPutPieceAbsolute4()
	{
		try {
			board.putPieceAbsolute(0, -1, new PieceCityCorner());
		} catch (Exception e) {
			fail(e.toString());
		}
		try {
			board.putPieceAbsolute(1, 0, new PieceCityCorner());
		} catch (Exception e) {
			fail(e.toString());
		}
		try {
			board.putPieceAbsolute(0, 1, new PieceCityCorner());
			fail("shouln'd fit");
		} catch (Exception e) {
			// good
		}
		try {
			board.putPieceAbsolute(0, -1, new PieceCityCorner());
			fail("shouln'd fit");
		} catch (Exception e) {
			// good
		}
	}

	/**
	 * Test of putPieceRelative method, of class Board.
	 */
	@Test
	public void testPutPieceRelative()
	{
		for (int i = 0; i < 10; ++i)
			try {
				board.putPieceRelative(-1, 0, new PieceCityPipe());
			} catch (Exception e) {
				fail("Relative point (-1,0) should always be valid.");
			}
	}

	/**
	 * Test of asArray method, of class Board.
	 */
	@Test
	public void testAsArray1() throws Exception
	{
		Piece[][] boardArray;

		boardArray = board.asArray();
		assertEquals(boardArray.length, 1);
		assertEquals(boardArray[0].length, 1);
		assertNotNull(boardArray[0][0]);
	}

	@Test
	public void testAsArray2() throws Exception
	{
		for (int i = 1; i <= 5; ++i) {
			board.putPieceAbsolute(0, i, new PieceCityPipe());
			board.putPieceAbsolute(i, 0, new PieceCityPipe());
		}

		Piece[][] boardArray = board.asArray();

		for (int i = 0; i < boardArray.length; ++i)
			for (int j = 0; j < boardArray[i].length; ++j)
				if (i == 0 || j == 0)
					assertNotNull(boardArray[i][j]);
				else
					assertNull(boardArray[i][j]);
	}

	/**
	 * Test of getPieceAbsolute method, of class Board.
	 */
	@Test
	public void testGetPieceAbsolute1()
	{
		Piece p = board.getPieceAbsolute(0, 0);
		assertNotNull(p);
		assertTrue(p instanceof PieceCityPipe);
	}

	@Test
	public void testGetPieceAbsolute2()
	{
		Piece p = board.getPieceAbsolute(0, 1);
		assertNull(p);
	}

	@Test
	public void testGetPieceAbsolute3() throws Exception
	{
		board.putPieceAbsolute(0, -1, new PieceCityPipe());
		for (int i = 1; i <= 5; ++i)
			board.putPieceAbsolute(i, 0, new PieceCityPipe());

		Piece p = board.getPieceAbsolute(0, -1);
		assertNotNull(p);
		assertTrue(p instanceof PieceCityPipe);

		p = board.getPieceAbsolute(5, 0);
		assertNotNull(p);
		assertTrue(p instanceof PieceCityPipe);
	}

	/**
	 * Test of removePieceAbsolute method, of class Board.
	 */
	public void testRemovePieceAbsolute()
	{
		Piece p = board.removePieceAbsolute(0, 0);
		assertNotNull(p);
		assertNull(board.removePieceAbsolute(0, 0));
	}

	/**
	 * Test of removePiece method, of class Board.
	 */
	public void testRemovePiece() throws Exception
	{
		Piece p = new PieceCityPipe();
		board.putPieceAbsolute(0, 1, p);
		assertEquals(p, board.removePiece(p));
		assertNull(board.getPieceAbsolute(0, 1));
	}

	/**
	 * Test of height method, of class Board.
	 */
	@Test
	public void testHeight() throws Exception
	{
		assertEquals(board.height(), 1);

		board.putPieceAbsolute(-1, 0, new PieceCityPipe());
		for (int i = 1; i <= 5; ++i)
			board.putPieceAbsolute(0, i, new PieceCityPipe());

		assertEquals(board.height(), 6);
	}

	/**
	 * Test of width method, of class Board.
	 */
	@Test
	public void testWidth() throws Exception
	{
		assertEquals(board.width(), 1);

		board.putPieceAbsolute(0, -1, new PieceCityPipe());
		for (int i = 1; i <= 5; ++i)
			board.putPieceAbsolute(i, 0, new PieceCityPipe());

		assertEquals(board.width(), 6);
	}
}
