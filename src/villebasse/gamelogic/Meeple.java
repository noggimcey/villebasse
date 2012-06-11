package villebasse.gamelogic;

import java.awt.Color;


public class Meeple
{
	public static double epsilon = 0.2;

	private Player player;
	private double x, y;

	public Meeple(Player player)
	{
		this.player = player;
	}

	public Color getColor()
	{
		return this.player.getColor();
	}

	public Player getPlayer()
	{
		return this.player;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public boolean isAt(double x, double y)
	{
		return Math.abs(this.x - x) < epsilon && Math.abs(this.y - y) < epsilon;
	}

	public void place(double x, double y)
	{
		this.x = Math.max(0, Math.min(1, x));
		this.y = Math.max(0, Math.min(1, y));
	}
}
