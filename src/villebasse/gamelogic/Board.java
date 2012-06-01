package villebasse.gamelogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Mallintaa pelilautaa.
 *
 * Lauta pitää huolen, etteivät asetettavat palat ole jo laudalla
 * ja että palat sopivat pyydettyyn paikkaa.
 */
public class Board
{
	public static final int defaultNumberOfPieces = 72;

	private HashMap<Point, Piece> pieces;
	private int maxX = 0, maxY = 0, minX = 0, minY = 0;


	/**
	 * Board-luokan konstruktori.
	 *
	 * @param initialPiece  Laudan aloituspala
	 */
	public Board(Piece initialPiece)
	{
		this(initialPiece, defaultNumberOfPieces);
	}

	/**
	 * Board-luokan konstruktori.
	 *
	 * @param initialPiece  Laudan aloituspala
	 * @param numberOfPieces  Monelleko palalle varataan tila (kasvaa automaattisesti)
	 */
	public Board(Piece initialPiece, int numberOfPieces)
	{
		this.pieces = new HashMap<Point, Piece>(numberOfPieces);
		this.put(0, 0, initialPiece);
	}

	/**
	 * Board-luokan konstruktori.
	 *
	 * Vetää aloituspalan annetusta pakasta ja varaa pakan koon verran tilaa.
	 *
	 * @param deck  Palapakka, josta aloituspala vedetään
	 * @throws DeckException  deck.draw()
	 */
	public Board(Deck deck) throws Exception
	{
		this.pieces = new HashMap<Point, Piece>(deck.size());
		this.put(0, 0, deck.draw());
	}

	/**
	 * Lauta taulukkona.
	 *
	 * @return Lauta taulukkona
	 */
	public Piece[][] asArray()
	{
		Piece[][] arr = new Piece[this.height()][this.width()];

		for (Map.Entry<Point, Piece> entry : this.pieces.entrySet()) {
			Point point = entry.getKey();
			int x = point.getX() - this.minX;
			int y = point.getY() - this.minY;
			arr[y][x] = entry.getValue();
		}

		return arr;
	}

	/**
	 * Aseta pala absoluuttisiin koordinaatteihin.
	 *
	 * Absoluuttiset koordinaatit lähtevät aloituspalasta (0,0) joka suuntaan.
	 *
	 * @param x  Absoluuttinen vaakasuuntainen koordinaatti
	 * @param y  Absoluuttinen pystysuuntainen koordinaatti
	 * @param piece  Laudalle asetteva pala
	 * @throws BoardException  Pala jo laudalla tai sopimaton sijainti
	 */
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

	/**
	 * Aseta pala suhteellisiin koordinaatteihin.
	 *
	 * Laudan vasemmanpuoleisin pala on suhteellisessa vaakakoordinaatissa 0
	 * ja ylimpänä oleva pala suhteellisessa pystykoordinaatissa 0.
	 *
	 * @param x  Suhteellinen vaakasuuntainen koordinaatti
	 * @param y  Suhteellinen pystysuuntainen koordinaatti
	 * @param piece  Laudalle asetteva pala
	 * @throws BoardException  Pala jo laudalla tai sopimaton sijainti
	 */
	public void putPieceRelative(int x, int y, Piece piece) throws Exception
	{
		this.putPieceAbsolute(x + this.minX, y + this.minY, piece);
	}

	/**
	 * Laudan korkeus.
	 *
	 * @return Laudan korkeus
	 */
	public int height()
	{
		return this.maxY - this.minY + 1;
	}

	/**
	 * Laudan leveys.
	 *
	 * @return Laudan leveys
	 */
	public int width()
	{
		return this.maxX - this.minX + 1;
	}


	private void checkBounds(int x, int y)
	{
		if (this.maxX < x)
			this.maxX = x;
		if (this.maxY < y)
			this.maxY = y;
		if (this.minX > x)
			this.minX = x;
		if (this.minY > y)
			this.minY = y;
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

	private class Point
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
