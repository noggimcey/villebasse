package villebasse.gamelogic;

import java.util.Random;
import java.util.Vector;


public abstract class Deck
{
	protected Vector<Piece> pieces;


	public Piece draw() throws Exception
	{
		if (this.isEmpty())
			throw new Exception("deck empty");

		return this.pieces.remove(this.size() - 1);
	}


	public boolean isEmpty()
	{
		return this.pieces.isEmpty();
	}


	public boolean putBack(Piece piece) throws Exception
	{
		if (this.pieces.contains(piece))
			return false;
			//throw new Exception("piece already in deck");

		this.pieces.add(piece);
		this.shuffle();
		return true;
	}


	public void shuffle()
	{
		FisherYatesShuffle(this.pieces);
	}


	public int size()
	{
		return this.pieces.size();
	}


	private void FisherYatesShuffle(Vector v)
	{
		Random rnd = new Random();
		for (int i = v.size() - 1; i > 0; --i) {
			int j = rnd.nextInt(i + 1);
			Object tmp = v.elementAt(i);
			v.set(i, v.elementAt(j));
			v.set(j, tmp);
		}
	}
}
