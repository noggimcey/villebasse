package villebasse.gamelogic;


public abstract class Piece
{
	public static enum Terrain {
		FIELD, ROAD, CITY, CLOISTER
	}

	protected Direction rotation = new Direction(Direction.NORTH);
	protected Terrain[] edges;


	public Terrain edge(Direction dir)
	{
		int i = dir.modulate(this.rotation).ordinal();

		if (i < this.edges.length)
			return this.edges[i];
		return null;
	}


	public Terrain[] edges()
	{
		Terrain[] rotatedEdges = new Terrain[4];

		// edges is rotation order
		for (Direction d : new Direction())
			rotatedEdges[d.ordinal()] = this.edge(d);

		return rotatedEdges;
	}


	// Rotate clock-wise
	public void rotate()
	{
		this.rotate(1);
	}


	public void rotate(int numberOfTimes)
	{
		this.rotation = this.rotation.rotateClockWise(numberOfTimes);
	}


	public void setRotation(int dir)
	{
		this.setRotation(new Direction(dir));
	}


	public void setRotation(Direction dir)
	{
		this.rotation = dir;
	}

	public String toString()
	{
		String ret = getClass().getName() + ": ";
		for (Terrain t : this.edges())
			ret += t + " ";

		return ret;
	}
}
