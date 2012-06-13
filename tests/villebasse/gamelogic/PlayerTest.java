package villebasse.gamelogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;


public class PlayerTest {

	Color color = Color.black;
	String name = "foo bar";
	int meeples = 1;
	Player player;

	@Before
	public void setUp() {
		player = new Player(name, color, meeples);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testTakeMeeple()
	{
		Meeple m;

		m = player.takeMeeple();
		assertNotNull(m);
		assertEquals(player.meeplesLeft(), meeples - 1);
		assertEquals(m.getColor(), color);
		assertEquals(m.getPlayer(), player);

		m = player.takeMeeple();
		assertNull(m);
	}

	@Test
	public void testHasMeeples()
	{
		assertTrue(player.hasMeeples());
		player.takeMeeple();
		assertFalse(player.hasMeeples());
	}

	@Test
	public void testReturnMeeple1()
	{
		assertFalse(player.returnMeeple(null, 10));
		Meeple m = new Meeple(new Player("wrong guy", Color.green));
		assertFalse(player.returnMeeple(m, 10));
	}

	@Test
	public void testReturnMeeple2()
	{
		int points = 9;
		Meeple m = player.takeMeeple();

		assertTrue(player.returnMeeple(m, points));
		assertEquals(player.getPoints(), points);
		assertEquals(player.meeplesLeft(), meeples);
	}
}
