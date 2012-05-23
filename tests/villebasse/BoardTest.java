package villebasse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of asArray method, of class Board.
     */
    @Test
    public void testAsArray() {
        System.out.println("asArray");
        Board instance = null;
        Piece[][] expResult = null;
        Piece[][] result = instance.asArray();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putPieceAbsolute method, of class Board.
     */
    @Test
    public void testPutPieceAbsolute() throws Exception {
        System.out.println("putPieceAbsolute");
        int x = 0;
        int y = 0;
        Piece piece = null;
        Board instance = null;
        instance.putPieceAbsolute(x, y, piece);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putPieceRelative method, of class Board.
     */
    @Test
    public void testPutPieceRelative() throws Exception {
        System.out.println("putPieceRelative");
        int x = 0;
        int y = 0;
        Piece piece = null;
        Board instance = null;
        instance.putPieceRelative(x, y, piece);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of height method, of class Board.
     */
    @Test
    public void testHeight() {
        System.out.println("height");
        Board instance = null;
        int expResult = 0;
        int result = instance.height();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of width method, of class Board.
     */
    @Test
    public void testWidth() {
        System.out.println("width");
        Board instance = null;
        int expResult = 0;
        int result = instance.width();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
