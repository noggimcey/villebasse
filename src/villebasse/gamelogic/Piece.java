package villebasse.gamelogic;


/**
 * Kuvaa laudalle asetettavaa laattaa.
 *
 * Abstrakti Piece-luokka toimii erilaisten laattojen vanhempana.
 */
public abstract class Piece
{
	public static enum Terrain {
		FIELD, ROAD, CITY, CLOISTER
	}

	protected Direction rotation = new Direction(Direction.NORTH);
	protected Terrain[] edges;
	protected Meeple meeple;

	/**
	 * Palan asento.
	 *
	 * @return Palan asento
	 */
	public Direction getRotation()
	{
		return this.rotation;
	}

	/**
	 * Palan maasto annetulla sivulla.
	 *
	 * @param direction  Suunta
	 * @return Maastotyyppi
	 */
	public Terrain edge(Direction direction)
	{
		int i = direction.modulate(this.rotation).ordinal();
		return this.edges[i];
	}

	/**
	 * Kaikkien sivujen maastotyypit.
	 *
	 * @return Maastotyypit pohjoisesta myötäpäivään
	 */
	public Terrain[] edges()
	{
		Terrain[] rotatedEdges = new Terrain[4];

		// edges is rotation order
		for (Direction d : new Direction())
			rotatedEdges[d.ordinal()] = this.edge(d);

		return rotatedEdges;
	}

	/**
	 * Palalla oleva nappula.
	 *
	 * @return Palalla olevat nappulat
	 */
	public Meeple getMeeple()
	{
		return this.meeple;
	}

	/**
	 * Onko palalla nappula.
	 *
	 * @return Onko palalla nappula
	 */
	public boolean hasMeeple()
	{
		return this.meeple != null;
	}

	/**
	 * Aseta nappula palalle.
	 *
	 * @param x  Vaakasuuntainen koordinaatti (0..1)
	 * @param y  Pystysuuntainen koordinaatti (0..1)
	 * @param meeple  Asetettava nappula
	 * @return Onnistuiko asettaminen
	 */
	public boolean placeMeeple(double x, double y, Meeple meeple)
	{
		if (meeple == null || this.hasMeeple())
			return false;

		meeple.place(x, y);
		this.meeple = meeple;

		return true;
	}

	/**
	 * Poista palalla oleva nappula.
	 *
	 * @return Poistettu nappula tai null
	 */
	public Meeple removeMeeple()
	{
		Meeple m = this.meeple;
		this.meeple = null;
		return m;
	}

	/**
	 * Pyöritä palaa 90 astetta myötäpäivään.
	 */
	public void rotate()
	{
		this.rotate(1);
	}

	/**
	 * Pyöritä palaa n * 90 astetta myötäpäivään.
	 *
	 * @param numberOfTimes  Kiertojen lukumäärä
	 */
	public void rotate(int numberOfTimes)
	{
		this.rotation = this.rotation.rotateClockWise(numberOfTimes);
	}

	/**
	 * Aseta palan asento.
	 *
	 * @param direction  Suunta
	 */
	public void setRotation(int direction)
	{
		this.setRotation(new Direction(direction));
	}

	/**
	 * Aseta palan asento.
	 *
	 * @param direction  Suunta
	 */
	public void setRotation(Direction direction)
	{
		this.rotation = direction;
	}

	/**
	 * Pala merkkijonona.
	 *
	 * @return Kuvaus merkkijonona
	 */
	public String toString()
	{
		String ret = getClass().getName() + ": ";
		for (Terrain t : this.edges())
			ret += t + " ";

		return ret;
	}
}
