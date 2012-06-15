package villebasse.ui.swing;

import java.util.EventObject;

/**
 * Välittää käyttäjän aiheuttamia tapahtumia.
 */
public class UserEvent extends EventObject
{
	public String command;

	/**
	 * Konstruktori.
	 *
	 * @param source  Tapahtuman lähde
	 */
	public UserEvent(Object source)
	{
		this(source, null);
	}

	/**
	 * Konstruktori.
	 *
	 * @param source  Tapahtuman lähde
	 * @param command  Tapahtumaa kuvaava komento
	 */
	public UserEvent(Object source, String command)
	{
		super(source);
		this.command = command;
	}
}
