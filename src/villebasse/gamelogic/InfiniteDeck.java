package villebasse.gamelogic;

import java.lang.reflect.Constructor;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Yhtä palatyyppiä loputtomasti ojentava pakka.
 *
 * Lähinnä testitarkoituksiin.
 */
public class InfiniteDeck extends Deck
{
	private Constructor<Piece> pieceConstructor;

	/**
	 * Konstruktori.
	 *
	 * @param name  Loputtomiin haluttavan palan luokan nimi
	 * @throws Exception  Luokkaa tai konstruktoria ei löydy
	 */
	public InfiniteDeck(String name) throws Exception
	{
		this(Piece.findClass(name));
	}

	/**
	 * Konstruktori.
	 *
	 * @param piece  Mallikappale
	 * @throws Exception  Luokkaa tai konstruktoria ei löydy
	 */
	public InfiniteDeck(Piece piece) throws Exception
	{
		this(piece.getClass());
	}

	/**
	 * Konstruktori.
	 *
	 * @param pieceClass  Luokka, jonka instansseja pakka antaa
	 * @throws Exception  Luokkaa tai konstruktoria ei löydy
	 */
	public InfiniteDeck(Class pieceClass) throws Exception
	{
		this.pieceConstructor = pieceClass.getConstructor();
	}

	public Piece draw()
	{
		try {
			if (this.pieceConstructor != null)
				return this.pieceConstructor.newInstance();
		} catch (Exception e) {
			System.err.println(e);
		}

		return null;
	}

	public boolean isEmpty()
	{
		return false;
	}

	public boolean putBack(Piece piece)
	{
		return true;
	}

	public int size()
	{
		return 1;
	}
}
