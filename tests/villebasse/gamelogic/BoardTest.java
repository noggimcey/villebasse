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
		board = new Board(new PieceBigCity());
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
			board.putPieceAbsolute(0, i, new PieceBigCity());
			board.putPieceAbsolute(i, 0, new PieceBigCity());
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
				board.putPieceAbsolute(i, 0, new PieceBigCity());
			} catch (Exception e) {
				fail("Absolute point ("+i+",0) should be reachable.");
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
				board.putPieceRelative(-1, 0, new PieceBigCity());
			} catch (Exception e) {
				fail("Relative point (-1,0) should always be valid.");
			}
	}

	/**
	 * Test of height method, of class Board.
	 */
	@Test
	public void testHeight() throws Exception
	{
		assertEquals(board.height(), 1);

		board.putPieceAbsolute(-1, 0, new PieceBigCity());
		for (int i = 1; i <= 5; ++i)
			board.putPieceAbsolute(0, i, new PieceBigCity());

		assertEquals(board.height(), 6);
	}

	/**
	 * Test of width method, of class Board.
	 */
	@Test
	public void testWidth() throws Exception
	{
		assertEquals(board.width(), 1);

		board.putPieceAbsolute(0, -1, new PieceBigCity());
		for (int i = 1; i <= 5; ++i)
			board.putPieceAbsolute(i, 0, new PieceBigCity());

		assertEquals(board.width(), 6);
	}
}
