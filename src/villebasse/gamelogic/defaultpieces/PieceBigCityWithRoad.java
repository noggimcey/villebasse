package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #######
 *   #######
 *   #######
 *   #/-%-\#
 *   /..%..\
 */


public class PieceBigCityWithRoad extends Piece
{
	public PieceBigCityWithRoad()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.CITY,
			Terrain.ROAD,
			Terrain.CITY,
		};
	}
}
