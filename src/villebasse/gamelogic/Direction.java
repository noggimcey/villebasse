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


	public Direction()
	{
		this(NORTH);
	}


	public Direction(int d)
	{
		this.dir = d;
	}


	public boolean equals(Direction that)
	{
		return this.dir == that.dir;
	}


	public Iterator<Direction> iterator()
	{
		return new DirectionIterator(this);
	}


	public Direction next()
	{
		return new Direction((this.dir + 1) % LAST);
	}


	public int ordinal()
	{
		return this.dir;
	}


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


	// Rotate clock-wise
	public Direction rotate(Direction d)
	{
		return this.rotate(d.ordinal());
	}


	public Direction rotate(int numberOfTimes)
	{
		Direction result = this;

		for (int i = 0; i < numberOfTimes; ++i)
			result = result.next();

		return result;
	}


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
