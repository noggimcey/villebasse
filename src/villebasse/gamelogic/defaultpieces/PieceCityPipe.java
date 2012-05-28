package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   \#####/
 *   .|###|.
 *   .|###|.
 *   .|###|.
 *   /#####\
 */


public class PieceCityPipe extends Piece
{
	public PieceCityPipe()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.CITY,
			Terrain.FIELD,
		};
	}
}
