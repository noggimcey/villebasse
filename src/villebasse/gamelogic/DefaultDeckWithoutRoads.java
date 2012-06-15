package villebasse.gamelogic;

import java.util.Vector;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Tietön pakka.
 *
 * Oletuspakan muunnelma.
 */
public class DefaultDeckWithoutRoads extends Deck
{
	public DefaultDeckWithoutRoads()
	{
		this.pieces = new Vector<Piece>(72);

		this.addPiecesByName("PieceBigCity", 4);
		this.addPiecesByName("PieceCityCorner", 2);
		this.addPiecesByName("PieceCityEnd", 4);
		this.addPiecesByName("PieceCityEndWithIntersection", 3);
		this.addPiecesByName("PieceCityPipe", 3);
		this.addPiecesByName("PieceCloister", 4);
		this.addPiecesByName("PieceDiagonalCity", 5);
		this.addPiecesByName("PieceFacingCities", 3);
		this.addPiecesByName("PieceFullCity", 1);
		this.addPiecesByName("PieceTIntersection", 4);
		this.addPiecesByName("PieceXIntersection", 1);

		this.shuffle();

		this.addPiecesByName("PieceCityEnd", 1);
	}
}
