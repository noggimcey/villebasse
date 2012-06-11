package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   .......
 *   ..===..
 *   .=====.
 *   ..===..
 *   ...%...
 */


public class PieceCloisterWithRoad extends Piece
{
	public PieceCloisterWithRoad()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.ROAD,
			Terrain.FIELD,
		};
	}
}
