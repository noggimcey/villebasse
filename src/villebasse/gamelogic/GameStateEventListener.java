package villebasse.gamelogic;

import java.util.EventListener;

/**
 * Rajapinta pelin tilan muutosten vastaanottamiseksi.
 */
public interface GameStateEventListener
{
	public void gameStateGameEnd(GameStateEvent gse);
	public void gameStateGameStart(GameStateEvent gse);
	public void gameStatePlaceMeeple(GameStateEvent gse);
	public void gameStateRemoveMeeples(GameStateEvent gse);
	public void gameStateRoundStart(GameStateEvent gse);
	public void gameStateTurnStart(GameStateEvent gse);
}

