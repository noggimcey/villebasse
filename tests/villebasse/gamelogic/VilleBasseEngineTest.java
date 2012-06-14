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


public class VilleBasseEngineTest
{

	VilleBasseEngine engine;
	Player[] players = new Player[]{
		new Player("player1", Color.black),
		new Player("player2", Color.blue),
		new Player("player3", Color.cyan),
		new Player("player4", Color.gray),
		new Player("player5", Color.green),
	};

	@Before
	public void setUp() throws Exception
	{
		engine = new VilleBasseEngine(new InfiniteDeck("PieceCloister"));
	}

	@After
	public void tearDown()
	{
	}


	@Test
	public void testAddPlayer()
	{
		for (Player p : players)
			assertTrue(engine.addPlayer(p));
		for (Player p : players)
			assertFalse(engine.addPlayer(p));
	}

	@Test
	public void testStartGame()
	{
		assertFalse(engine.startGame());

		for (Player p : players)
			assertTrue(engine.addPlayer(p));

		assertTrue(engine.startGame());
	}

	@Test
	public void testPutPiece()
	{
		assertFalse(engine.putPiece(-1, 0));

		for (Player p : players)
			assertTrue(engine.addPlayer(p));

		assertFalse(engine.putPiece(-1, 0));

		assertTrue(engine.startGame());

		assertTrue(engine.putPiece(-1, 0));
		assertFalse(engine.putPiece(-1, 0));
		assertFalse(engine.putPiece(1, 0));
	}

	@Test
	public void testPlaceMeeple()
	{
		assertFalse(engine.placeMeeple(0.5, 0.5));

		for (Player p : players)
			assertTrue(engine.addPlayer(p));

		assertFalse(engine.placeMeeple(0.5, 0.5));

		assertTrue(engine.startGame());
		assertFalse(engine.placeMeeple(0.5, 0.5));
		assertTrue(engine.putPiece(-1, 0));

		assertTrue(engine.placeMeeple(0.5, 0.5));
		assertFalse(engine.placeMeeple(0.5, 0.5));
		assertFalse(engine.placeMeeple(0.1, 0.9));
	}

	@Test
	public void testNextTurn()
	{
		for (Player p : players)
			assertTrue(engine.addPlayer(p));

		assertTrue(engine.startGame());
	}
}
