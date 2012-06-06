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
	}
}
