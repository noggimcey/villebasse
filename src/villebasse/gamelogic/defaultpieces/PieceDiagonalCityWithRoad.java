package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*  #######
 *  ######.
 *  ####..%
 *  ##..%%.
 *  #..%...
 */

public class PieceDiagonalCityWithRoad extends Piece
{
	public PieceDiagonalCityWithRoad ()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.ROAD,
			Terrain.ROAD,
			Terrain.CITY,
		};
	}
}
