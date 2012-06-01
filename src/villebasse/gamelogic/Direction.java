package villebasse.gamelogic;

import java.util.Iterator;


public class Direction implements Iterable<Direction>
{
	public  static final int NORTH = 0;
	public  static final int EAST  = 1;
	public  static final int SOUTH = 2;
	public  static final int WEST  = 3;

	private static final int LAST  = 4;

	private int dir;


	/**
	 * Oletuskonstruktori.
	 *
	 * Osoittaa pohjoiseen.
	 */
	public Direction()
	{
		this(NORTH);
	}

	/**
	 * Konstruktori annetulla suunnalla.
	 *
	 * @param direction  Suunta
	 * @throws DirectionException  Sopimaton suunta
	 */
	public Direction(int direction)
	{
		this.dir = direction;
	}

	/**
	 * Vertailuoperaatio.
	 *
	 * @param that  Vertailun toinen osapuoli
	 * @return Osoittavatko instanssit samaan suuntaan
	 */
	public boolean equals(Direction that)
	{
		return this.dir == that.dir;
	}

	/**
	 * Suuntaiteraattori.
	 *
	 * Käy kaikki suunnat läpi aloittaen annetusta suunnasta.
	 *
	 * @return Iteraattori
	 */
	public Iterator<Direction> iterator()
	{
		return new DirectionIterator(this);
	}

	/**
	 * Seuraava suunta myötäpäivään.
	 *
	 * @return Seuraava suunta
	 */
	public Direction next()
	{
		return new Direction((this.dir + 1) % LAST);
	}

	/**
	 *
	 *
	 * @param direction
	 * @return Moduloitu suunta
	 */
	public Direction modulate(Direction direction)
	{
		return this.rotateAntiClockWise(direction.ordinal());
	}

	/**
	 * Suunta kokonaislukuna.
	 *
	 * @return Suunta kokonaislukuna (0..3)
	 */
	public int ordinal()
	{
		return this.dir;
	}

	/**
	 * Vastakkainen suunta.
	 *
	 * @return Vastakkainen suunta
	 */
	public Direction opposite()
	{
		if (this.dir == NORTH)
			return new Direction(SOUTH);
		else if (this.dir == EAST)
			return new Direction(WEST);
		else if (this.dir == SOUTH)
			return new Direction(NORTH);
		else if (this.dir == WEST)
			return new Direction(EAST);
		return null;
	}

	/**
	 * Seuraava suunta vastapäivään.
	 *
	 * @return Edellinen suunta
	 */
	public Direction prev()
	{
		return new Direction((this.dir - 1 + LAST) % LAST);
	}

	/**
	 * Suunta kierrettynä n * 90 astetta vastapäivään.
	 *
	 * @param numberOfTimes  Kiertojen lukumäärä
	 * @return Uusi suunta
	 */
	public Direction rotateAntiClockWise(int numberOfTimes)
	{
		Direction result = this;

		for (int i = 0; i < numberOfTimes; ++i)
			result = result.prev();

		return result;
	}

	/**
	 * Suunta kierrettynä n * 90 astetta myötäpäivään.
	 *
	 * @param numberOfTimes  Kiertojen lukumäärä
	 * @return Uusi suunta
	 */
	public Direction rotateClockWise(int numberOfTimes)
	{
		Direction result = this;

		for (int i = 0; i < numberOfTimes; ++i)
			result = result.next();

		return result;
	}

	/**
	 * Suunta merkkijonoja.
	 *
	 * @return Ilmansuunnan nimi
	 */
	public String toString()
	{
		if (this.dir == NORTH)
			return "north";
		else if (this.dir == EAST)
			return "east";
		else if (this.dir == SOUTH)
			return "south";
		else if (this.dir == WEST)
			return "west";
		else
			return "unknown";
	}


	public class DirectionIterator implements Iterator
	{
		private Direction start;
		private Direction next;


		public DirectionIterator(Direction start)
		{
			this.start = this.next = start;
		}


		public boolean hasNext()
		{
			return this.next != null;
		}


		public Direction next()
		{
			Direction cur = this.next;
			this.next = cur.next();

			if (this.next.equals(this.start))
				this.next = null;

			return cur;
		}


		public void remove() {}
	}
}
