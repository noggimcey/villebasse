package villebasse.ui.swing;

import java.util.ListIterator;
import java.util.Vector;
import javax.swing.JPanel;

/**
 * Välittää laudalla tapahtuvia painalluksia.
 */

public abstract class JPanelUserEventSource extends JPanel
{
	private Vector<UserEventListener> listeners = new Vector(2);

	public void addUserEventListener(UserEventListener l)
	{
		if (l != null)
			this.listeners.add(l);
	}

	public void dispatchEvent(BoardClickEvent bc)
	{
		for (UserEventListener uel : this.listeners)
			if (uel instanceof BoardClickEventListener)
				((BoardClickEventListener) uel).boardClickEventOccurred(bc);
	}

	public void dispatchEvent(UserEvent ue)
	{
		for (UserEventListener uel : this.listeners)
			uel.userEventOccurred(ue);
	}

	public void removeUserEventListener(UserEventListener uel)
	{
		this.listeners.remove(uel);
	}

	public void setUserEventListener(UserEventListener uel)
	{
		this.listeners = new Vector<UserEventListener>(2);
		if (uel != null)
			this.addUserEventListener(uel);
	}
}
