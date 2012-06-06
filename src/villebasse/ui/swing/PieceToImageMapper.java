package villebasse.ui.swing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import villebasse.gamelogic.*;


public abstract class PieceToImageMapper
{
	protected String baseDirectory, prefix, postfix;
	protected HashMap<String, Vector<BufferedImage>> images = new HashMap(16);
	protected HashMap<Piece, BufferedImage> manufactured = new HashMap(72);


	public BufferedImage map()
	{
		return map(null);
	}

	public BufferedImage map(Piece piece)
	{
		BufferedImage image = checkAlreadyMade(piece);

		if (image == null) {
			image = randomImage(pieceClass(piece));
			if (image != null && piece != null)
				this.manufactured.put(piece, image);
		}

		return image;
	}


	protected boolean addImages(String pieceType, String[] files)
		throws IOException
	{
		Vector<BufferedImage> newImages = openImages(files);
		if (newImages == null || newImages.size() == 0)
			return false;

		Vector<BufferedImage> oldImages = this.images.get(pieceType);
		if (oldImages == null)
			this.images.put(pieceType, newImages);
		else
			oldImages.addAll(newImages);

		return true;
	}

	protected BufferedImage checkAlreadyMade(Piece piece)
	{
		if (piece == null)
			return null;
		return this.manufactured.get(piece);
	}

	private String fullname(String name)
	{
		return this.baseDirectory + File.separator +
			this.prefix + name + this.postfix;
	}

	protected BufferedImage openImage(String filename) throws IOException
	{
		File f = new File(fullname(filename));

		try {
			BufferedImage i = ImageIO.read(f);
			return i;
		} catch (IllegalArgumentException e) {
			System.err.println(e + ": " + f);
			return null;
		} catch (IOException e) {
			System.err.println(e + ": " + f);
			throw e;
		}
	}

	protected Vector<BufferedImage> openImages(String[] filenames)
		throws IOException
	{
		Vector<BufferedImage> images = new Vector(filenames.length);

		for (String filename : filenames)
			images.add(openImage(filename));

		return images;
	}

	protected String pieceClass(Piece piece)
	{
		if (piece == null)
			return "null";
		return piece.getClass().getName();
	}

	protected BufferedImage randomImage(String name)
	{
		Vector<BufferedImage> images = this.images.get(name);
		if (images == null || images.size() == 0)
			return null;
		return select(images);
	}

	protected BufferedImage select(Vector<BufferedImage> images)
	{
		int len = images.size();

		if (len == 0)
			return null;
		if (len == 1)
			return images.firstElement();

		Random rnd = new Random();
		return images.elementAt(rnd.nextInt(len));
	}
}
