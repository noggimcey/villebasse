package villebasse.gamelogic;

import java.util.Random;
import java.util.Vector;

/**
 * Mallintaa sekoitettua palapakkaa.
 *
 * Abstrakti Deck-luokka toimii todellisten pakkojen vanhempana.
 */
public abstract class Deck
{
	protected Vector<Piece> pieces;


	/**
	 * Vetää pakasta seuraavan palan.
	 *
	 * @return Seuraava pala
	 * @throws DeckException  Pakka tyhjä
	 */
	public Piece draw() throws Exception
	{
		if (this.isEmpty())
			throw new Exception("deck empty");

		return this.pieces.remove(this.size() - 1);
	}

	/**
	 * Testaa, onko pakka tyhjä.
	 *
	 * @return Onko pakka tyhjä
	 */
	public boolean isEmpty()
	{
		return this.pieces.isEmpty();
	}

	/**
	 * Laittaa palan pakkaan.
	 *
	 * Epäonnistuu, jos pala on jo pakassa.
	 *
	 * @param piece  Laitettava pala
	 * @return Onnistuiko laitto
	 */
	public boolean putBack(Piece piece)
	{
		if (this.pieces.contains(piece))
			return false;

		this.pieces.add(piece);
		this.shuffle();
		return true;
	}

	/**
	 * Sekoittaa pakan.
	 */
	public void shuffle()
	{
		FisherYatesShuffle(this.pieces);
	}

	/**
	 * Pakassa jäljellä olevien palojen lukumäärä.
	 *
	 * @return Jäljellä olevien palojen määrä
	 */
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
