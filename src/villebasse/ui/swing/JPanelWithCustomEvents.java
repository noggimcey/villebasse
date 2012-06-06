package villebasse.ui.swing;

import java.util.Vector;
import javax.swing.JPanel;

/**
 * JPanel-luokan laajennos, joka vastaa räätälöityjen tapahtumien
 * propagoimisen AWT/Swing -hierarkiaa ylös.
 */

public class JPanelWithCustomEvents extends JPanel
{
	private Vector<BoardEventListener> boardEventListeners = new Vector(2);

	/**
	 * Lisää BoardEvent-kuuntelija.
	 *
	 * @param listener  Kuuntelija
	 */
	public void addBoardEventListener(BoardEventListener listener)
	{
		this.boardEventListeners.add(listener);
	}

	/**
	 * Välitä tapahtuma.
	 *
	 * @param boardEvent  Välitettävä tapahtuma.
	 */
	public void dispatchEvent(BoardEvent boardEvent)
	{
		if (boardEventListeners.isEmpty())
			((JPanelWithCustomEvents) this.getParent()).dispatchEvent(boardEvent);
		else
			for (BoardEventListener listener : this.boardEventListeners)
				listener.boardEventOccurred(boardEvent);
	}
}
