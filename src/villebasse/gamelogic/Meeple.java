package villebasse.gamelogic;

import java.awt.Color;

/**
 * Laudalle asettettava pelinappula.
 */
public class Meeple
{
	public static double epsilon = 0.2;

	private Player player;
	private double x, y;

	/**
	 * Konstruktori.
	 *
	 * @param player  Pelaaja, jolle nappula kuuluu
	 */
	public Meeple(Player player)
	{
		this.player = player;
	}

	/**
	 * Pelinappulan väri.
	 *
	 * @return Pelinappulan väri
	 */
	public Color getColor()
	{
		return this.player.getColor();
	}

	/**
	 * Pelaaja, jolle nappula kuuluu.
	 *
	 * @return Pelaaja, jolle nappula kuuluu
	 */
	public Player getPlayer()
	{
		return this.player;
	}

	/**
	 * Nappulan vaakasuuntainen sijainti laatalla.
	 *
	 * @return Nappulan vaakasuuntainen sijainti (0..1)
	 */
	public double getX()
	{
		return this.x;
	}

	/**
	 * Nappulan pystysuuntainen sijainti laatalla.
	 *
	 * @return Nappulan pystysuuntainen sijainti (0..1)
	 */
	public double getY()
	{
		return this.y;
	}

	/**
	 * Onko nappula annetussa pisteessä.
	 *
	 * @return Onko nappula annetussa pisteessä
	 */
	public boolean isAt(double x, double y)
	{
		return Math.abs(this.x - x) < epsilon && Math.abs(this.y - y) < epsilon;
	}

	/**
	 * Aseta nappula laatalle.
	 *
	 * @param x  Vaakasuuntainen sijainti (0..1);
	 * @param y  Pystysuuntainen sijainti (0..1);
	 */
	public void place(double x, double y)
	{
		this.x = Math.max(0, Math.min(1, x));
		this.y = Math.max(0, Math.min(1, y));
	}
}
