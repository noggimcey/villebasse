package villebasse.gamelogic;

import java.util.Vector;
import villebasse.gamelogic.defaultpieces.*;


public class DefaultDeckWithoutRoads extends Deck
{
	public DefaultDeckWithoutRoads()
	{
		this.pieces = new Vector<Piece>(72);

		for (int i = 0; i < 5; ++i)
			this.pieces.add(new PieceDiagonalCity());

		for (int i = 0; i < 3; ++i)
			this.pieces.add(new PieceCityEndWithIntersection());

		for (int i = 0; i < 3; ++i)
			this.pieces.add(new PieceFacingCities());

		for (int i = 0; i < 4; ++i)
			this.pieces.add(new PieceTIntersection());

		for (int i = 0; i < 4; ++i)
			this.pieces.add(new PieceBigCity());

		for (int i = 0; i < 2; ++i)
			this.pieces.add(new PieceCityCorner());

		for (int i = 0; i < 4; ++i)
			this.pieces.add(new PieceCityEnd());

		for (int i = 0; i < 3; ++i)
			this.pieces.add(new PieceCityPipe());

		for (int i = 0; i < 4; ++i)
			this.pieces.add(new PieceCloister());

		this.pieces.add(new PieceFullCity());

		this.shuffle();
		this.pieces.add(new PieceCityEnd());
	}
}
