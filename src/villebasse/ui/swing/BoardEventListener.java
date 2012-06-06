package villebasse.ui.swing;

import java.util.EventListener;

/**
 * BoardEvent-viestien kuuntelurajapinta.
 */

public interface BoardEventListener extends EventListener
{
	/**
	 * Välitä BoardEvent-viesti.
	 *
	 * @param boardEvent  Välitettävä viesti
	 */
	public void boardEventOccurred(BoardEvent boardEvent);
}
