package villebasse.ui.swing;

/**
 * Välittää laudalla tapahtuvia painalluksia.
 *
 * Laajentaa yleistä käyttäjän aiheuttamaa tapahtumaa
 */
public class BoardClickEvent extends UserEvent
{
	public static final int BUTTON1 = 1;
	public static final int BUTTON2 = 2;

	public int x, y, button;
	public double dx, dy;

	/**
	 * BoardClick-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 */
	public BoardClickEvent(Object source)
	{
		this(source, 0, 0);
	}

	/**
	 * BoardClick-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param button  Painettu nappi
	 */
	public BoardClickEvent(Object source, int button)
	{
		this(source, 0, 0, button);
	}

	/**
	 * BoardClick-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param x  Tapahtuman vaakasuuntainen koordinaatti
	 * @param y  Tapahtuman pystysuuntainen koordinaatti
	 */
	public BoardClickEvent(Object source, int x, int y)
	{
		this(source, x, y, BUTTON1);
	}

	/**
	 * BoardClick-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param x  Tapahtuman vaakasuuntainen koordinaatti
	 * @param y  Tapahtuman pystysuuntainen koordinaatti
	 * @param button  Painettu nappi
	 */
	public BoardClickEvent(Object source, int x, int y, int button)
	{
		this(source, x, y, button, 0, 0);
	}

	/**
	 * BoardClick-luokan konstruktori.
	 *
	 * @param source  Tapahtuman aiheuttanut objekti
	 * @param x  Tapahtuman vaakasuuntainen koordinaatti
	 * @param y  Tapahtuman pystysuuntainen koordinaatti
	 * @param dx  Vaakasuuntaisen koordinaatin desimaaliosa
	 * @param dy  Pystysuuntaisen koordinaatin desimaaliosa
	 * @param button  Painettu nappi
	 */
	public BoardClickEvent(Object source, int x, int y,
		double dx, double dy, int button)
	{
		super(source);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.button = button;
	}
}
