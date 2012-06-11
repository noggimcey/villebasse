package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   .......
 *   ..===..
 *   .=====.
 *   ..===..
 *   .......
 */


public class PieceCloister extends Piece
{
	public PieceCloister()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.FIELD,
		};
	}
}
