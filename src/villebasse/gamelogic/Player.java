package villebasse.gamelogic;

import java.awt.Color;

/**
 * Peliin osallistuva pelaaja.
 *
 */

public class Player
{
	private Color color;
	private String name;
	private int meeplesLeft;
	private int points = 0;

	/**
	 * Konstruktori.
	 *
	 * @param name  Pelaajan nimi
	 * @param color  Pelaajan pelinappuloiden väri
	 */
	public Player(String name, Color color)
	{
		this(name, color, 7);
	}

	/**
	 * Konstruktori.
	 *
	 * @param name  Pelaajan nimi
	 * @param color  Pelaajan pelinappuloiden väri
	 * @param meeplesLeft  Montako pelinappulaa pelaajalla on
	 */
	public Player(String name, Color color, int meeplesLeft)
	{
		this.color = color;
		this.name = name;
		this.meeplesLeft = meeplesLeft;
	}

	/**
	 * Pelaajan väri.
	 *
	 * @return Pelaajan väri
	 */
	public Color getColor()
	{
		return this.color;
	}

	/**
	 * Paljonko pelaajalla on pisteitä.
	 *
	 * @return Pelaajan pistemäärä
	 */
	public int getPoints()
	{
		return this.points;
	}

	/**
	 * Pelaajan nimi.
	 *
	 * @return Pelaajan nimi
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Onko pelaajalla pelinappuloita jäljellä.
	 *
	 * @return Onko pelaajalla pelinappuloita jäljellä
	 */
	public boolean hasMeeples()
	{
		return this.meeplesLeft > 0;
	}

	/**
	 * Palauta pelaajan pelinappula ja lisää pelaajalle pisteitä.
	 *
	 * @param meeple  Palautettava pelinappula
	 * @param points  Lisättävä pistemäärä (voi olla negatiivinen)
	 * @return Onnistuiko nappulan palauttaminen
	 */
	public boolean returnMeeple(Meeple meeple, int points)
	{
		if (meeple == null || meeple.getPlayer() != this)
			return false;

		this.meeplesLeft++;
		this.points += points;
		return true;
	}

	/**
	 * Montako pelinappulaa pelaajalla on.
	 *
	 * @return Montako pelinappulaa pelaajalla on jäljellä
	 */
	public int meeplesLeft()
	{
		return this.meeplesLeft;
	}

	/**
	 * Ota pelaajalta yksi pelinappula.
	 *
	 * @return Pelaajalle kuulava pelinappula tai null, jos pelaajalla ei ole
	 *         nappuloita jäljellä.
	 */
	public Meeple takeMeeple()
	{
		if (!this.hasMeeples())
			return null;

		this.meeplesLeft--;
		return new Meeple(this);
	}
}
