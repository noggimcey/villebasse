package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*  #######
 *  ######.
 *  ####...
 *  ##.....
 *  #......
 */

public class PieceDiagonalCity extends Piece
{
	public PieceDiagonalCity()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.CITY,
		};
	}
}
