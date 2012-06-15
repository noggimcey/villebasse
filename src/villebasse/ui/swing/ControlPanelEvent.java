package villebasse.ui.swing;

/**
 * TODO
 */

public class ControlPanelEvent extends UserEvent
{
	public String command;

	public ControlPanelEvent(Object source)
	{
		this(source, null);
	}

	public ControlPanelEvent(Object source, String command)
	{
		super(source);
		this.command = command;
	}
}
