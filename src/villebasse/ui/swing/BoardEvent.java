package villebasse.ui.swing;

import java.awt.AWTEvent;


public class BoardEvent extends AWTEvent
{
	public int x, y;

	public BoardEvent(Object source)
	{
		this(source, 0, 0);
	}

	public BoardEvent(Object source, int type)
	{
		this(source, 0, 0, type);
	}

	public BoardEvent(Object source, int x, int y)
	{
		this(source, x, y, 0);
	}

	public BoardEvent(Object source, int x, int y, int type)
	{
		super(source, type);
		this.x = x;
		this.y = y;
	}
}
