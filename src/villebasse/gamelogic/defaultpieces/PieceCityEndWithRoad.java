package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ...%..#
 *   ...%.##
 *   ...%.##
 *   ...%.##
 *   ...%..#
 */

public class PieceCityEndWithRoad extends Piece
{
	public PieceCityEndWithRoad ()
	{
		this.edges = new Terrain[]{
			Terrain.ROAD,
			Terrain.CITY,
			Terrain.ROAD,
			Terrain.FIELD,
		};
	}
}
