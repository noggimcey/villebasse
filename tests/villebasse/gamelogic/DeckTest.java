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
	public void testDraw1() {
		int size = deck.size();

		for (int i = 0; i < size; ++i) {
			try {
				assertNotNull(deck.draw());
			} catch (Exception e) {
				fail(e.toString());
			}
		}
	}

	@Test
	public void testDraw2() {
		int size = deck.size();

		for (int i = 0; i < size; ++i) {
			try {
				assertNotNull(deck.draw());
			} catch (Exception e) {
				fail(e.toString());
			}
		}

		try {
			deck.draw();
			fail("deck should be empty");
		} catch (Exception e) {
			// good
		}
	}

	@Test
	public void testIsEmpty() {
		int size = deck.size();

		for (int i = 0; i < size; ++i) {
			try {
				assertNotNull(deck.draw());
			} catch (Exception e) {
				fail(e.toString());
			}
		}

		assertTrue(deck.isEmpty());
	}

	@Test
	public void testPutBack() throws Exception {
		Piece p = deck.draw();

		assertTrue(deck.putBack(p));
		assertFalse(deck.putBack(p));
	}

	@Test
	public void testShuffle() {
	}

	@Test
	public void testSize() {
		int size = deck.size();

		Piece p = null;
		try {
			p = deck.draw();
		} catch (Exception e) {
			fail();
		}

		assertEquals(deck.size(), size - 1);
		deck.putBack(p);
		assertEquals(deck.size(), size);
	}
}
