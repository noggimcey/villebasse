package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #######
 *   #######
 *   #######
 *   #######
 *   #######
 */
public class PieceFullCity extends Piece
{
	public PieceFullCity()
	{
		this.edges = new Terrain[]{
			Terrain.CITY,
			Terrain.CITY,
			Terrain.CITY,
			Terrain.CITY,
		};
	}
}
