package villebasse.ui.swing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import villebasse.gamelogic.*;

/**
 * Abstrakti luokka, joka kuvaa palat (Piece) näytettäviksi
 * kuviksi (BufferedImage).
 *
 * Annetuista kuvista arvotaan palalle sen luokkaa vastaava kuva. Jos palalle
 * on jo kertaalleen arvottu kuva, annetaan joka kerta sama kuva.
 */

public abstract class PieceToImageMapper
{
	protected String baseDirectory, prefix, postfix;
	protected HashMap<String, Vector<BufferedImage>> images = new HashMap(16);
	protected HashMap<Piece, BufferedImage> cache = new HashMap(72);


	/**
	 * Hae tyhjää palaa vastaava kuva.
	 */
	public BufferedImage map()
	{
		return map(null);
	}

	/**
	 * Hae palaa vastaava kuva.
	 *
	 * @param piece  Pala, jolle kuva haetaan
	 *
	 * @return Kuvaa vastaava pala tai 'null', jos sopivaa kuvaa ei löydy
	 */
	public BufferedImage map(Piece piece)
	{
		BufferedImage image = checkCache(piece);

		if (image == null) {
			image = randomImage(pieceClass(piece));
			if (image != null && piece != null)
				this.cache.put(piece, image);
		}

		return image;
	}


	/**
	 * Lisää kuvia mahdollisten kuvien joukkoon.
	 *
	 * @param pieceType  Luokka, johon kuvat liittyvät
	 * @param files  Lisättävät tiedostot
	 *
	 * @return Lisättiin yhtään kuvaa
	 */
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

	/**
	 * Tarkasta, onko palalle jo kertaalleen arvottu kuva.
	 *
	 * @param piece  Pala, jolle kuva haetaan
	 *
	 * @return Aiemmin arvottu kuva tai 'null'
	 */
	protected BufferedImage checkCache(Piece piece)
	{
		if (piece == null)
			return null;
		return this.cache.get(piece);
	}

	/**
	 * Kasaa tiedostonimi annetuista pätkistä.
	 *
	 * @param name  Nimen vaihtuva osa
	 *
	 * @return Koko tiedostonimi
	 */
	protected String fullname(String name)
	{
		return this.baseDirectory + File.separator +
			this.prefix + name + this.postfix;
	}

	/**
	 * Avaa tiedosto ja lue kuvaksi.
	 *
	 * @param filename  Avattavan tiedoston nimen vaihtuva osa
	 *
	 * @return Tiedosto kuvana
	 */
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

	/**
	 * Avaa lista tiedostoja kuviksi.
	 *
	 * @param filenames  Lista avattavia tiedostoja
	 *
	 * @return Lista avattuja kuvia
	 */
	protected Vector<BufferedImage> openImages(String[] filenames)
		throws IOException
	{
		Vector<BufferedImage> images = new Vector(filenames.length);

		for (String filename : filenames)
			images.add(openImage(filename));

		return images;
	}

	/**
	 * Arvo annetun luokan nimeä vastaa kuva
	 *
	 * @param name  Luokan nimi
	 *
	 * @return Satunnainen kuva tai 'null'
	 */
	protected BufferedImage randomImage(String name)
	{
		Vector<BufferedImage> images = this.images.get(name);
		if (images == null || images.size() == 0)
			return null;
		return select(images);
	}

	/**
	 * Palan luokka
	 *
	 * @param piece  Pala
	 *
	 * @return Palan luokka
	 */
	protected static String pieceClass(Piece piece)
	{
		if (piece == null)
			return "null";
		return piece.getClass().getName();
	}


	private static BufferedImage select(Vector<BufferedImage> images)
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
