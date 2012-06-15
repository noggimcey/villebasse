package villebasse.ui.swing;

import java.util.EventObject;

/**
 * Välittää käyttäjän aiheuttamia tapahtumia.
 */

public class UserEvent extends EventObject
{
	public String command;

	public UserEvent(Object source)
	{
		this(source, null);
	}

	public UserEvent(Object source, String command)
	{
		super(source);
		this.command = command;
	}
}
