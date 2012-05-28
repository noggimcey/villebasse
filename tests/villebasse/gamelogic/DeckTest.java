package villebasse.gamelogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DeckTest {

	Deck deck;

	@Before
	public void setUp() {
		deck = new DefaultDeckWithoutRoads();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testDraw() {
		int size = deck.size();

		for (int i = 0; i < size; ++i) {
			try {
				assertNotNull(deck.draw());
			} catch (Exception e) {
				fail(e.toString());
			}
		}

		assertTrue(deck.isEmpty());

		try {
			deck.draw();
			fail("deck should be empty");
		} catch (Exception e) {
			// good
		}
	}

	@Test
	public void testIsEmpty() {
	}

	@Test
	public void testPutBack() throws Exception {
		Piece p = deck.draw();

		try {
			deck.putBack(p);
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			deck.putBack(p);
			fail("piece already in deck");
		} catch (Exception e) {
			// good
		}
	}

	@Test
	public void testShuffle() {
	}

	@Test
	public void testSize() {
	}

}
