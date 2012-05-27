package villebasse;

import java.util.Vector;


public class DefaultDeckWithoutRoads extends Deck
{
	public DefaultDeckWithoutRoads()
	{
		this.pieces = new Vector<Piece>(72);

		for (int i = 0; i < 72; ++i)
			this.pieces.add(new Piece(i));

		this.shuffle();
	}
}
