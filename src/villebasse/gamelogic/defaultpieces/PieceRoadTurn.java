package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   .......
 *   .......
 *   %%%%...
 *   ...%...
 *   ...%...
 */
public class PieceRoadTurn extends Piece
{
	public PieceRoadTurn()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.ROAD,
			Terrain.ROAD,
		};
	}
}
