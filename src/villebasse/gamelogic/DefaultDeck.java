package villebasse.gamelogic;

import java.util.Vector;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Oletuspakka alkuperäisillä laatoilla ja laattamäärillä.
 */

public class DefaultDeck extends Deck
{
	public DefaultDeck()
	{
		this.pieces = new Vector<Piece>(72);

		this.addPiecesByName("PieceBigCity", 4);
		this.addPiecesByName("PieceBigCityWithRoad", 3);
		this.addPiecesByName("PieceCityCorner", 2);
		this.addPiecesByName("PieceCityEnd", 5);
		this.addPiecesByName("PieceCityEndWithIntersection", 3);
		this.addPiecesByName("PieceCityEndWithRoad", 3);
		this.addPiecesByName("PieceCityEndWithRoadLeft", 3);
		this.addPiecesByName("PieceCityEndWithRoadRight", 3);
		this.addPiecesByName("PieceCityPipe", 3);
		this.addPiecesByName("PieceCloister", 4);
		this.addPiecesByName("PieceCloisterWithRoad", 3);
		this.addPiecesByName("PieceDiagonalCity", 5);
		this.addPiecesByName("PieceDiagonalCityWithRoad", 5);
		this.addPiecesByName("PieceFacingCities", 3);
		this.addPiecesByName("PieceFullCity", 1);
		this.addPiecesByName("PieceRoad", 8);
		this.addPiecesByName("PieceRoadTurn", 9);
		this.addPiecesByName("PieceTIntersection", 4);
		this.addPiecesByName("PieceXIntersection", 1);

		this.shuffle();

		this.addPiecesByName("PieceCityEndWithRoad", 1);
	}
}
