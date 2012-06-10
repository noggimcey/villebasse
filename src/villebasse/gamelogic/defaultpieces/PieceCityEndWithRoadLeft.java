package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ...%..#
 *   ...%.##
 *   ...%.##
 *   ...%.##
 *   ...%..#
 */

public class PieceCityEndWithRoadLeft extends Piece
{
	public PieceCityEndWithRoadLeft ()
	{
		this.edges = new Terrain[]{
			Terrain.ROAD,
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.ROAD,
		};
	}
}
