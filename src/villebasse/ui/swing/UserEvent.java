package villebasse.ui.swing;

import java.util.EventObject;

/**
 * Välittää laudalla tapahtuvia painalluksia.
 */

public abstract class UserEvent extends EventObject
{
	public UserEvent(Object source)
	{
		super(source);
	}
}
