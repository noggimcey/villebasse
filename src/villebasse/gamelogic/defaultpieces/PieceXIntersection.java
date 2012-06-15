package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ...%...
 *   ...%...
 *   %%%%%%%
 *   ...%...
 *   ...%...
 */
public class PieceXIntersection extends Piece
{
	public PieceXIntersection()
	{
		this.edges = new Terrain[]{
			Terrain.ROAD,
			Terrain.ROAD,
			Terrain.ROAD,
			Terrain.ROAD,
		};
	}
}
