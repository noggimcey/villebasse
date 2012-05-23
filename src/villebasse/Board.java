package villebasse;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class Board
{
	public static final int defaultNumberOfPieces = 72;

	private HashMap<Point, Piece> pieces;
	private int max_x = 0, max_y = 0, min_x = 0, min_y = 0;


	public Board(Piece initial_piece)
	{
		this(initial_piece, defaultNumberOfPieces);
	}

	public Board(Piece initial_piece, int number_of_pieces)
	{
		this.pieces = new HashMap<Point, Piece>(number_of_pieces);
		this.put(0, 0, initial_piece);
	}


	public Piece[][] asArray()
	{
		Piece[][] arr = new Piece[this.height()][this.width()];

		for (Map.Entry<Point, Piece> entry : this.pieces.entrySet()) {
			Point point = entry.getKey();
			int x = point.getX() - this.min_x;
			int y = point.getY() - this.min_y;
			arr[y][x] = entry.getValue();
		}

		return arr;
	}

	public void putPieceAbsolute(int x, int y, Piece piece) throws Exception
	{
		Point point = new Point(x, y);

		if (this.has(point))
			throw new Exception("piece already on board");

		if (!this.hasNeighbour(point))
			throw new Exception("no neighbour");

		this.put(point, piece);
		this.checkBounds(x, y);
	}

	public void putPieceRelative(int x, int y, Piece piece) throws Exception
	{
		this.putPieceAbsolute(x + this.min_x, y + this.min_y, piece);
	}


	private void checkBounds(int x, int y)
	{
		if (this.max_x < x)
			this.max_x = x;
		if (this.max_y < y)
			this.max_y = y;
		if (this.min_x > x)
			this.min_x = x;
		if (this.min_y > y)
			this.min_y = y;
	}

	private void checkBounds(Point point)
	{
		this.checkBounds(point.getX(), point.getY());
	}


	private Piece get(int x, int y)
	{
		return this.get(new Point(x, y));
	}

	private Piece get(Point point)
	{
		return this.pieces.get(point);
	}


	private boolean has(int x, int y)
	{
		return this.has(new Point(x, y));
	}

	private boolean has(Point point)
	{
		return this.pieces.containsKey(point);
	}


	private boolean hasNeighbour(int x, int y)
	{
		return this.hasNeighbour(new Point(x, y));
	}

	private boolean hasNeighbour(Point point)
	{
		for (Point neighbour : point.neighbours())
			if (this.has(neighbour))
				return true;
		return false;
	}


	private Piece put(int x, int y, Piece piece)
	{
		return this.put(new Point(x, y), piece);
	}

	private Piece put(Point point, Piece piece)
	{
		return this.pieces.put(point, piece);
	}


	public int height()
	{
		return this.max_y - this.min_y + 1;
	}

	public int width()
	{
		return this.max_x - this.min_x + 1;
	}


	public class Point
	{
		private int x;
		private int y;

		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object that)
		{
			return that instanceof Point
				&& this.x == ((Point)that).getX()
				&& this.y == ((Point)that).getY();
		}

		public int hashCode()
		{
			return this.x * Board.defaultNumberOfPieces * 2 + this.y;
		}


		public int getX()
		{
			return this.x;
		}

		public int getY()
		{
			return this.y;
		}

		public Vector<Point> neighbours()
		{
			Vector<Point> neighbours = new Vector<Point>(4);

			neighbours.add(new Point(this.x - 1, this.y    ));
			neighbours.add(new Point(this.x,     this.y - 1));
			neighbours.add(new Point(this.x    , this.y + 1));
			neighbours.add(new Point(this.x + 1, this.y    ));

			return neighbours;
		}
	}
}
