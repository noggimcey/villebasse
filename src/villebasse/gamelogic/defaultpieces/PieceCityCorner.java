package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   ....../
 *   .....|#
 *   .....|#
 *   .____|#
 *   /#####\
 */

public class PieceCityCorner extends Piece
{
	public PieceCityCorner()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,           
			Terrain.CITY,            
			Terrain.CITY,            
			Terrain.FIELD,           
		};
	}
}
