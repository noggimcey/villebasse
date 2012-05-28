package villebasse.gamelogic;


public abstract class Piece
{
	//public static final
	private int state;
	private int rotation = 0;
	private int[] edges;

	public String toString()
	{
		return getClass().getName() + '(' + this.state + ')';
	}

	public int[] edges()
	{
		int nEdges = this.edges.length;
		int[] rotatedEdges = new int[nEdges];

		for (int i = 0; i < nEdges; ++i)
			rotatedEdges[i] = this.edges[(this.rotation + 1) % nEdges];

		return rotatedEdges;
	}
}
