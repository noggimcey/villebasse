package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   .......
 *   .......
 *   %%%%%%%
 *   ...%...
 *   ...%...
 */
public class PieceTIntersection extends Piece
{
	public PieceTIntersection()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,
			Terrain.ROAD,
			Terrain.ROAD,
			Terrain.ROAD,
		};
	}
}
