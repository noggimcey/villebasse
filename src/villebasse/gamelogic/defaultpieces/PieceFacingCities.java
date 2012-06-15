package villebasse.gamelogic.defaultpieces;

import villebasse.gamelogic.Piece;


/*   #.....#
 *   ##...##
 *   ##...##
 *   ##...##
 *   #.....#
 */
public class PieceFacingCities extends Piece
{
	public PieceFacingCities()
	{
		this.edges = new Terrain[]{
			Terrain.FIELD,
			Terrain.CITY,
			Terrain.FIELD,
			Terrain.CITY,
		};
	}
}
