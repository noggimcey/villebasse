package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ...%...
 *   ...%...
 *   ...%...
 *   ...%...
 *   ...%...
 */
public class PieceRoad extends Piece
{
	public PieceRoad()
	{
		this.edges = new Terrain[]{
			Terrain.ROAD,
			Terrain.FIELD,
			Terrain.ROAD,
			Terrain.FIELD,
		};
	}
}
