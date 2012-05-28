package villebasse.gamelogic;

import java.util.Vector;
import villebasse.gamelogic.defaultpieces.*;


public class DefaultDeckWithoutRoads extends Deck
{
	public DefaultDeckWithoutRoads()
	{
		this.pieces = new Vector<Piece>(72);

		for (int i = 0; i < 72 / 2; ++i)
			this.pieces.add(new PieceBigCity());

		for (int i = 1; i < 72 / 2; ++i)
			this.pieces.add(new PieceCityCorner());

		this.shuffle();
		this.pieces.add(new PieceCityPipe());
	}
}
