package villebasse.ui.swing;

import java.util.EventListener;


public interface BoardEventListener extends EventListener
{
	public void boardEventOccurred(BoardEvent be);
}
