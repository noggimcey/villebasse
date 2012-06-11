package villebasse.gamelogic;

import java.awt.Color;


public class Player
{
	private Color color;
	private String name;
	private int meeplesLeft;
	private int points = 0;

	public Player(String name, Color color)
	{
		this(name, color, 7);
	}

	public Player(String name, Color color, int meeplesLeft)
	{
		this.color = color;
		this.name = name;
		this.meeplesLeft = meeplesLeft;
	}

	public Color getColor()
	{
		return this.color;
	}

	public String getName()
	{
		return this.name;
	}

	public boolean hasMeeples()
	{
		return this.meeplesLeft > 0;
	}

	public int returnMeeple(int points)
	{
		this.meeplesLeft++;
		this.points += points;
		return this.points;
	}

	public Meeple takeMeeple()
	{
		if (!this.hasMeeples())
			return null;

		this.meeplesLeft--;
		return new Meeple(this);
	}
}
