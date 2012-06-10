package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #######
 *   .#####.
 *   ......%
 *   ....%%.
 *   ...%...
 */

public class PieceCityEndWithRoadRight extends Piece
{
	public PieceCityEndWithRoadRight ()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.ROAD,
			Terrain.ROAD,
			Terrain.FIELD,
		};
	}
}
