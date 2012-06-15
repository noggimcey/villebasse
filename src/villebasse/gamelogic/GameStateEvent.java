package villebasse.gamelogic;

import java.util.EventObject;

/**
 * Pelin tilan muutoksesta ilmoittava tapahtuma.
 */
public class GameStateEvent extends EventObject
{
	/**
	 * Konstruktori.
	 *
	 * @param source  VilleBasseEngine johon tapahtuma liittyy
	 */
	public GameStateEvent(VilleBasseEngine source)
	{
		super(source);
	}
}
