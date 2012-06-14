package villebasse.ui.swing;


public class ScannedMapper extends PieceToImageMapper
{
	public ScannedMapper() throws Exception
	{
		this.baseDirectory = "../../../pieces_256";
		this.prefix = "maskd_";
		this.postfix = "_256x256.gif";

		addImages("PieceCityCorner", new String[]{
			"020",
			"023",
		});
		addImages("PieceCityPipe", new String[]{
			"030",
		});
		addImages("PieceBigCity", new String[]{
			"002",
			"005",
			"008",
		});
		addImages("PieceDiagonalCityWithRoad", new String[]{
			"003",
			"006",
			"012",
		});
		addImages("PieceCityEndWithIntersection", new String[]{
			"004",
			"007",
			"010",
		});
		addImages("PieceBigCityWithRoad", new String[]{
			"014",
		});
		addImages("PieceCityEndWithRoadRight", new String[]{
			"015",
			"021",
			"027",
		});
		addImages("PieceCityEndWithRoad", new String[]{
			"016",
			"019",
			"022",
		});
		addImages("PieceCityEndWithRoadLeft", new String[]{
			"018",
			"024",
			"026",
		});
		addImages("PieceFacingCities", new String[]{
			"031",
			"032",
			"033",
		});
		addImages("PieceDiagonalCity", new String[]{
			"034",
			"035",
			"039",
		});
		addImages("PieceCityEnd", new String[]{
			"036_rot",
			"037_rot",
			"041",
			"042",
			"045",
		});
		addImages("PieceRoadTurn", new String[]{
			"046",
			"047",
			"056",
			"061",
			"063",
			"064",
			"067",
			"069",
			"071",
		});
		addImages("PieceRoad", new String[]{
			"050",
			"052",
			"055",
			"060",
			"062",
			"065",
			"066",
			"070",
		});
		addImages("PieceTIntersection", new String[]{
			"051",
			"058",
			"059",
			"068",
		});
		addImages("PieceXIntersection", new String[]{
			"057",
		});
		addImages("PieceCloister", new String[]{
			"043",
			"044",
			"053",
			"054",
		});
		addImages("PieceCloisterWithRoad", new String[]{
			"048",
			"049",
		});
		addImages("PieceFullCity", new String[]{
			"013",
		});
	}
}
