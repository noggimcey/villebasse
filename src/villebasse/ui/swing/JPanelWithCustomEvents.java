package villebasse.ui.swing;

import java.util.Vector;
import javax.swing.JPanel;


public class JPanelWithCustomEvents extends JPanel
{
	private Vector<BoardEventListener> boardEventListeners = new Vector(2);

	public synchronized void addBoardEventListener(BoardEventListener listener)
	{
		this.boardEventListeners.add(listener);
	}

	public synchronized void dispatchEvent(BoardEvent be)
	{
		if (boardEventListeners.isEmpty())
			((JPanelWithCustomEvents) this.getParent()).dispatchEvent(be);
		else
			for (BoardEventListener listener : this.boardEventListeners)
				listener.boardEventOccurred(be);
	}
}
