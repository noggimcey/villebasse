package villebasse.gamelogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Random;


public class MeepleTest {

	Color color = Color.black;
	Meeple meeple;
	Player player;

	@Before
	public void setUp() {
		player = new Player("foo foo", color);
		meeple = new Meeple(player);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetColor()
	{
		assertEquals(color, meeple.getColor());
	}

	@Test
	public void testPlace()
	{
		meeple.place(0.2, 0.8);
		assertEquals(meeple.getX(), 0.2, 0.001);
		assertEquals(meeple.getY(), 0.8, 0.001);

		meeple.place(-1, 10);
		assertEquals(meeple.getX(), 0, 0.001);
		assertEquals(meeple.getY(), 1, 0.001);
	}

	@Test
	public void testIsAt()
	{
		Random rnd = new Random();

		double x, y, a, b;
		for (int i = 0; i < 10; ++i) {
			x = rnd.nextDouble();
			y = rnd.nextDouble();
			meeple.place(x, y);

			a = x + 1.1 * Meeple.epsilon * (rnd.nextBoolean() ? 1 : -1);
			b = y + 1.1 * Meeple.epsilon * (rnd.nextBoolean() ? 1 : -1);
			assertFalse(meeple.isAt(a, b));

			for (int j = 0; j < 10; ++j) {
				a = x + (2 * rnd.nextDouble() - 1) * Meeple.epsilon;
				b = y + (2 * rnd.nextDouble() - 1) * Meeple.epsilon;
				assertTrue(meeple.isAt(a, b));
			}
		}
	}
}
