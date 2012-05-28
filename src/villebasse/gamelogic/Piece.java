package villebasse.gamelogic;


public abstract class Piece
{
	public static enum Terrain {
		FIELD, ROAD, CITY, CLOISTER
	}

	protected int state;
	protected Direction rotation = new Direction(Direction.NORTH);
	protected Terrain[] edges;


	public String toString()
	{
		String ret = getClass().getName() + ": ";
		for (Terrain t : this.edges())
			ret += t + " ";

		return ret;
	}


	public Terrain edge(Direction dir)
	{
		int i = dir.ordinal();

		if (i < this.edges.length)
			return this.edges[i];
		return null;
	}


	// TODO: returns edges in wrong order
	public Terrain[] edges()
	{
		Terrain[] rotatedEdges = new Terrain[4];

		// edges is rotation order
		for (Direction d : new Direction())
			rotatedEdges[d.ordinal()] = this.edges[d.rotate(this.rotation).ordinal()];

		return rotatedEdges;
	}


	// Rotate clock-wise
	public void rotate()
	{
		this.rotate(1);
	}


	public void rotate(int numberOfTimes)
	{
		this.rotation = this.rotation.rotate(numberOfTimes);
	}


	public void setRotation(int dir)
	{
		this.setRotation(new Direction(dir));
	}


	public void setRotation(Direction dir)
	{
		this.rotation = dir;
	}
}
