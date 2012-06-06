package villebasse.gamelogic;

import java.awt.Point;
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

	private HashMap<MyPoint, Piece> pieces;
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
		this.pieces = new HashMap<MyPoint, Piece>(numberOfPieces);
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
		this.pieces = new HashMap<MyPoint, Piece>(deck.size());
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

		for (Map.Entry<MyPoint, Piece> entry : this.pieces.entrySet()) {
			MyPoint point = entry.getKey();
			int x = point.x - this.minX;
			int y = point.y - this.minY;
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
		MyPoint point = new MyPoint(x, y);

		if (this.has(point))
			throw new Exception("piece already on board");

		if (!this.checkNeighbours(point, piece))
			throw new Exception("no sufficient neighbours");

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

	private void checkBounds(MyPoint point)
	{
		this.checkBounds(point.x, point.y);
	}

	private boolean checkNeighbours(MyPoint point, Piece piece)
	{
		boolean ok = false;

		for (Direction d : new Direction()) {
			Piece neighbour = this.get(point.go(d));
			if (neighbour != null) {
				if (neighbour.edge(d.opposite()) == piece.edge(d))
					ok = true;
				else
					return false;
			}
		}

		return ok;
	}

	private Piece get(int x, int y)
	{
		return this.get(new MyPoint(x, y));
	}

	private Piece get(MyPoint point)
	{
		return this.pieces.get(point);
	}

	private boolean has(int x, int y)
	{
		return this.has(new MyPoint(x, y));
	}

	private boolean has(MyPoint point)
	{
		return this.pieces.containsKey(point);
	}

	/*
	private boolean hasNeighbour(int x, int y)
	{
		return this.hasNeighbour(new MyPoint(x, y));
	}

	private boolean hasNeighbour(MyPoint point)
	{
		for (MyPoint neighbour : point.neighbours())
			if (this.has(neighbour))
				return true;
		return false;
	}
	*/

	private Piece put(int x, int y, Piece piece)
	{
		return this.put(new MyPoint(x, y), piece);
	}

	private Piece put(MyPoint point, Piece piece)
	{
		return this.pieces.put(point, piece);
	}

	private class MyPoint extends Point
	{
		public MyPoint(MyPoint mp)
		{
			super(mp);
		}

		public MyPoint(int x, int y)
		{
			super(x, y);
		}

		public MyPoint go(Direction d)
		{
			MyPoint p = new MyPoint(this);

			if (d.equals(Direction.NORTH))
				p.translate(0, -1);
			else if (d.equals(Direction.EAST))
				p.translate(1, 0);
			else if (d.equals(Direction.SOUTH))
				p.translate(0, 1);
			else if (d.equals(Direction.WEST))
				p.translate(-1, 0);

			return p;
		}
	}
}
