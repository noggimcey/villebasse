package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
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

public class GUIPiece extends JPanelWithCustomEvents
	//implements MouseListener
{
	private static PieceToImageMapper mapper = new NullMapper();

	private BufferedImage image;
	private Piece piece;
	private AffineTransform tx = new AffineTransform();
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
		//this.addMouseListener(this);

		setImageRotation();
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
			((Graphics2D) graphics).drawImage(this.image, transform(size), null);
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
		at.concatenate(this.tx);
		at.translate(-size / 2, -size / 2);
		at.scale(size / w, size / h);
		return at;
	}

	private void setImageRotation()
	{
		if (this.piece == null)
			return;
		setImageRotation(this.piece.getRotation().ordinal());
	}

	private void setImageRotation(int rotation)
	{
		AffineTransform at = new AffineTransform();
		rotateImage(rotation);
	}

	private void rotateImage(int rotation)
	{
		if (rotation < 0)
			for (int i = -rotation; i > 0; --i)
				this.tx.concatenate(new AffineTransform(0, -1, 1, 0, 0, 0));
		else if (rotation > 0)
			for (int i = rotation; i > 0; --i)
				this.tx.concatenate(new AffineTransform(0, 1, -1, 0, 0, 0));
	}

	/*
	public void mouseReleased(MouseEvent e)
	{
		this.dispatchEvent(new BoardEvent(this, this.x, this.y));
		this.rotateClockWise();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	*/
}
