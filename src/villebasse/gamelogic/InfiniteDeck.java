package villebasse.gamelogic;

import java.lang.reflect.Constructor;
import villebasse.gamelogic.defaultpieces.*;

public class InfiniteDeck extends Deck
{
	private Constructor<Piece> pieceConstructor;


	public InfiniteDeck(String name) throws Exception
	{
		this(Piece.findClass(name));
	}

	public InfiniteDeck(Piece piece) throws Exception
	{
		this(piece.getClass());
	}

	public InfiniteDeck(Class pieceClass) throws Exception
	{
		this.pieceConstructor = pieceClass.getConstructor();
	}

	public Piece draw()
	{
		if (this.pieceConstructor == null)
			return null;

		try {
			return this.pieceConstructor.newInstance();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
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
