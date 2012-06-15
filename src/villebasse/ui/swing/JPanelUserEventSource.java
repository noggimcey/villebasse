package villebasse.ui.swing;

import java.util.ListIterator;
import java.util.Vector;
import javax.swing.JPanel;

/**
 * JPanel:in abstrakti aliluokka, joka tuoteuttaa UserEventListenerien
 * käsittelyn.
 */
public abstract class JPanelUserEventSource extends JPanel
{
	private Vector<UserEventListener> listeners = new Vector(2);

	/**
	 * Lisää käyttäjän aiheuttaman tapahtuman kuuntelija.
	 *
	 * @param uel  Kuuntelija
	 */
	public void addUserEventListener(UserEventListener uel)
	{
		if (uel != null)
			this.listeners.add(uel);
	}

	/**
	 * Välitä BoardClick-tapahtuma kuuntelijoille.
	 *
	 * @param bc  Välitettävä tapahtuma
	 */
	public void dispatchEvent(BoardClickEvent bc)
	{
		for (UserEventListener uel : this.listeners)
			if (uel instanceof BoardClickEventListener)
				((BoardClickEventListener) uel).boardClickEventOccurred(bc);
	}

	/**
	 * Välitä tapahtuma kuuntelijoille.
	 *
	 * @param ue  Välitettävä tapahtuma
	 */
	public void dispatchEvent(UserEvent ue)
	{
		for (UserEventListener uel : this.listeners)
			uel.userEventOccurred(ue);
	}

	/**
	 * Poista käyttäjän aiheuttaman tapahtuman kuuntelija.
	 *
	 * @param uel  Kuuntelija
	 */
	public void removeUserEventListener(UserEventListener uel)
	{
		this.listeners.remove(uel);
	}

	/**
	 * Aseta käyttäjän aiheuttaman tapahtuman kuuntelija.
	 *
	 * @param uel  Kuuntelija
	 */
	public void setUserEventListener(UserEventListener uel)
	{
		this.listeners = new Vector<UserEventListener>(2);
		if (uel != null)
			this.addUserEventListener(uel);
	}
}
