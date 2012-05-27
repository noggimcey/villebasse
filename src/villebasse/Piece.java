package villebasse;


public class Piece
{
	protected int state;

	public Piece()
	{
		this(0);
	}

	public Piece(int state)
	{
		this.state = state;
	}

	public String toString()
	{
		return getClass().getName() + '(' + this.state + ')';
	}
}
