package villebasse.gamelogic;


public abstract class Piece
{
	public static enum Terrain {
		FIELD, ROAD, CITY, CLOISTER
	}

	public static enum EdgeDirection {
		NORTH, WEST, SOUTH, EAST
	}

	protected int state;
	protected EdgeDirection rotation = EdgeDirection.NORTH;
	protected Terrain[] edges;


	public String toString()
	{
		String ret = getClass().getName() + ": ";
		for (Terrain t : this.edges())
			ret += t + " ";

		return ret;
	}


	public Terrain[] edges()
	{
		int nEdges = this.edges.length;
		Terrain[] rotatedEdges = new Terrain[nEdges];

		// edges is rotation order
		for (int i = 0; i < nEdges; ++i)
			rotatedEdges[i] = this.edges[(i + this.rotation.ordinal()) % nEdges];

		return rotatedEdges;
	}


	public void setRotation(EdgeDirection dir)
	{
		this.rotation = dir;
	}
}
