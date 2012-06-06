package villebasse.ui.swing;

import java.awt.image.BufferedImage;


public class NullMapper extends PieceToImageMapper
{
	public NullMapper()
	{
	}

	public BufferedImage map(String name)
	{
		return null;
	}
}
