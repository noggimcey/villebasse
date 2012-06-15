package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
//import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import villebasse.gamelogic.*;

/**
 * Laudalla näkyvä pala.
 *
 * Kätkee sisäänsä palan (Piece) ja palaa vastaavan kuvan ja tarjoaa
 * rajapinnan niiden yhdessä käsittelyyn.
 */
public class GUIPiece extends JPanel
{
	private static PieceToImageMapper mapper = new NullMapper();

	private BufferedImage image;
	private Piece piece;
	private AffineTransform rotation = new AffineTransform();
	private boolean rotateable;

	/**
	 * Aseta luokan käyttämä PieceToImageMapper.
	 *
	 * @param newMapper  Käytettäväksi asettava PieceToImageMapper
	 * @return Edellinen PieceToImageMapper
	 */
	public static PieceToImageMapper setMapper(PieceToImageMapper newMapper)
	{
		PieceToImageMapper oldMapper = mapper;
		mapper = newMapper;
		return oldMapper;
	}


	/**
	 * GUIPiece-luokan tyhjä konstruktori.
	 */
	public GUIPiece()
	{
		this(null);
	}

	/**
	 * GUIPiece-luokan konstruktori.
	 *
	 * @param piece  Instanssin käyttämä pala
	 */
	public GUIPiece(Piece piece)
	{
		this(piece, false);
	}

	/**
	 * GUIPiece-luokan konstruktori.
	 *
	 * @param piece  Instanssin käyttämä pala
	 * @param rotateable  Onko pala pyöritettävissä
	 */
	public GUIPiece(Piece piece, boolean rotateable)
	{
		this.image = this.mapper.map(piece);
		this.piece = piece;
		this.rotateable = rotateable;

		setImageRotation();
	}

	/**
	 * Palan käyttämä kuva.
	 *
	 * @return Kuva
	 */
	public BufferedImage getImage()
	{
		return this.image;
	}

	/**
	 * Palan asento.
	 *
	 * @return Palan asento
	 */
	public AffineTransform getTransform()
	{
		this.setImageRotation();
		return new AffineTransform(this.rotation);
	}

	/**
	 * Piirrä pala annetulle Graphics-instanssille.
	 *
	 * @param graphics  Graphics-instanssi, jolla GUIPiece piirretään
	 */
	public void paint(Graphics graphics)
	{
		int size = Math.min(this.getWidth(), this.getHeight());

		if (this.image != null) {
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.drawImage(this.image, this.transform(size), null);
			if (this.piece.hasMeeple()) {
				Meeple m = this.piece.getMeeple();
				g2d.setPaint(m.getColor());
				g2d.fill(this.meepleShape(m, size));
			}
		} else {
			graphics.drawRect(1, 1, size - 2, size - 2);
		}
	}

	/**
	 * Pyöritä palaa 90 astetta vastapäivään.
	 */
	public void rotateAntiClockWise()
	{
		if (this.rotateable) {
			rotateImage(-1);
			this.piece.rotate(3);
			this.revalidate();
		}
	}

	/**
	 * Pyöritä palaa 90 astetta myötäpäivään.
	 */
	public void rotateClockWise()
	{
		if (this.rotateable) {
			rotateImage(1);
			this.piece.rotate();
			this.repaint();
		}
	}


	private AffineTransform transform(double size)
	{
		int h = this.image.getHeight();
		int w = this.image.getWidth();
		AffineTransform at = new AffineTransform();

		at.translate(size / 2, size / 2);
		at.concatenate(this.rotation);
		at.translate(-size / 2, -size / 2);
		at.scale(size / w, size / h);
		return at;
	}

	private Shape meepleShape(Meeple m, int size)
	{
		double r = size * 0.1;
		double x = m.getX() * size;
		double y = m.getY() * size;
		return new Ellipse2D.Double(x - r , y - r, 2 * r, 2 * r);
	}

	private void setImageRotation()
	{
		if (this.piece == null)
			return;
		setImageRotation(this.piece.getRotation().ordinal());
	}

	private void setImageRotation(int rotation)
	{
		this.rotation = new AffineTransform();
		rotateImage(rotation);
	}

	private void rotateImage(int rotation)
	{
		if (rotation < 0)
			for (int i = -rotation % 4; i > 0; --i)
				this.rotation.concatenate(new AffineTransform(0, -1, 1, 0, 0, 0));
		else if (rotation > 0)
			for (int i = rotation % 4; i > 0; --i)
				this.rotation.concatenate(new AffineTransform(0, 1, -1, 0, 0, 0));
	}
}
