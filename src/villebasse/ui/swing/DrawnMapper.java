package villebasse.ui.swing;

/**
 * PieceToImageMapper oma piirtämille paloille.
 */
public class DrawnMapper extends PieceToImageMapper
{
	public DrawnMapper() throws Exception
	{
		this.baseDirectory = "../../data/pieces";
		this.prefix = "";
		this.postfix = "_256x256.gif";

		addImages("PieceBigCity", new String[]{"BigCity"});
		addImages("PieceBigCityWithRoad", new String[]{"BigCityWithRoad"});
		addImages("PieceCityCorner", new String[]{"CityCorner"});
		addImages("PieceCityEnd", new String[]{"CityEnd"});
		addImages("PieceCityEndWithIntersection", new String[]{"CityEndWithIntersection"});
		addImages("PieceCityEndWithRoadLeft", new String[]{"CityEndWithRoadLeft"});
		addImages("PieceCityEndWithRoad", new String[]{"CityEndWithRoad"});
		addImages("PieceCityEndWithRoadRight", new String[]{"CityEndWithRoadRight"});
		addImages("PieceCityPipe", new String[]{"CityPipe"});
		addImages("PieceCloister", new String[]{"Cloister"});
		addImages("PieceCloisterWithRoad", new String[]{"CloisterWithRoad"});
		addImages("PieceDiagonalCity", new String[]{"DiagonalCity"});
		addImages("PieceDiagonalCityWithRoad", new String[]{"DiagonalCityWithRoad"});
		addImages("PieceFacingCities", new String[]{"FacingCities"});
		addImages("PieceFullCity", new String[]{"FullCity"});
		addImages("PieceRoad", new String[]{"Road"});
		addImages("PieceRoadTurn", new String[]{"RoadTurn"});
		addImages("PieceTIntersection", new String[]{"TIntersection"});
		addImages("PieceXIntersection", new String[]{"XIntersection"});
	}
}
