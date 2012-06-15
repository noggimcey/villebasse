package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #######
 *   #######
 *   #######
 *   #/---\#
 *   /.....\
 */
public class PieceBigCity extends Piece
{
	public PieceBigCity()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.CITY,
		};
	}
}
