package villebasse.ui.swing;


public class ScannedMapper extends PieceToImageMapper
{
	public ScannedMapper() throws Exception
	{
		this.baseDirectory = "../../../pieces_256";
		this.prefix = "maskd_";
		this.postfix = "_256x256.gif";

		addImages("villebasse.gamelogic.defaultpieces.PieceCityCorner",
			new String[]{
				"020",
				"023",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityPipe",
			new String[]{
				"030",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceBigCity",
			new String[]{
				"002",
				"005",
				"008",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceDiagonalCityWithRoad",
			new String[]{
				"003",
				"006",
				"012",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityEndWithIntersection",
			new String[]{
				"004",
				"007",
				"010",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceBigCityWithRoad",
			new String[]{
				"014",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityEndWithRoadRight",
			new String[]{
				"015",
				"021",
				"027",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityEndWithRoad",
			new String[]{
				"016",
				"019",
				"022",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityEndWithRoadLeft",
			new String[]{
				"018",
				"024",
				"026",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceFacingCities",
			new String[]{
				"031",
				"032",
				"033",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceDiagonalCity",
			new String[]{
				"034",
				"035",
				"039",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceCityEnd",
			new String[]{
				"036_rot",
				"037_rot",
				"041",
				"042",
				"045",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceRoadTurn",
			new String[]{
				"046",
				"047",
				"056",
				"061",
				"063",
				"064",
				"067",
				"069",
				"071",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceRoad",
			new String[]{
				"050",
				"052",
				"055",
				"060",
				"062",
				"065",
				"066",
				"070",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceTIntersection",
			new String[]{
				"051",
				"058",
				"059",
				"068",
			}
		);
		addImages("villebasse.gamelogic.defaultpieces.PieceXIntersection",
			new String[]{
				"057",
			}
		);
	}
}
