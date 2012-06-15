package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #######
 *   .#####.
 *   .......
 *   .......
 *   .......
 */
public class PieceCityEnd extends Piece
{
	public PieceCityEnd()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.FIELD,
			Terrain.FIELD,
		};
	}
}
