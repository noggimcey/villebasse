package villebasse;

import java.util.Vector;


public class DefaultDeckWithoutRoads extends Deck
{
	public DefaultDeckWithoutRoads()
	{
		this.pieces = new Vector<Piece>(72);

		for (int i = 1; i < 72; ++i)
			this.pieces.add(new Piece(i));

		this.shuffle();
		this.pieces.add(new Piece(0));
	}
}
