package villebasse.ui.swing;

import java.awt.AWTEvent;

/**
 * Välittää laudalla tapahtuvia painalluksia.
 */

public class BoardEvent extends AWTEvent
{
	public int x, y;
	public double dx, dy;

	/**
	 * BoardEvent-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 */
	public BoardEvent(Object source)
	{
		this(source, 0, 0);
	}

	/**
	 * BoardEvent-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param type  Viestin tyyppi
	 */
	public BoardEvent(Object source, int type)
	{
		this(source, 0, 0, type);
	}

	/**
	 * BoardEvent-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param x  Tapahtuman vaakasuuntainen koordinaatti
	 * @param y  Tapahtuman pystysuuntainen koordinaatti
	 */
	public BoardEvent(Object source, int x, int y)
	{
		this(source, x, y, 0);
	}

	/**
	 * BoardEvent-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param x  Tapahtuman vaakasuuntainen koordinaatti
	 * @param y  Tapahtuman pystysuuntainen koordinaatti
	 * @param type  Viestin tyyppi
	 */
	public BoardEvent(Object source, int x, int y, int type)
	{
		super(source, type);
		this.x = x;
		this.y = y;
	}

	public BoardEvent(Object source, int x, int y, double dx, double dy, int type)
	{
		super(source, type);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
}
