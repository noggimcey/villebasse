package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ...%..#
 *   ...%.##
 *   %%%%.##
 *   ...%.##
 *   ...%..#
 */
public class PieceCityEndWithIntersection extends Piece
{
	public PieceCityEndWithIntersection ()
	{
		this.edges = new Terrain[]{
			Terrain.ROAD,
			Terrain.CITY,
			Terrain.ROAD,
			Terrain.ROAD,
		};
	}
}
