package villebasse.ui.swing;

import java.awt.image.BufferedImage;

/**
 * PieceToImageMapper-implementaatio, joka kuvaa jokaisen palan
 * tyhjälle kuvalle.
 */
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
