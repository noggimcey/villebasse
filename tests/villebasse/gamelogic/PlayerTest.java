package villebasse.gamelogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;


public class Player {

	Color color = Color.black;
	Player player;

	@Before
	public void setUp() {
		player = new Player("foo foo", color);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testHasMeeples()
	{
	}

	@Test
	public void testReturnMeeple()
	{
	}

	@Test
	public void testTakeMeeple()
	{
	}
}
