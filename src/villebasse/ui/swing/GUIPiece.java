package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import villebasse.gamelogic.*;


public class GUIPiece extends JPanelWithCustomEvents
	//implements MouseListener
{
	private static PieceToImageMapper mapper = new NullMapper();

	private BufferedImage image;
	private Piece piece;
	private AffineTransform tx = new AffineTransform();
	private boolean rotateable;


	public static PieceToImageMapper setMapper(PieceToImageMapper newMapper)
	{
		PieceToImageMapper oldMapper = mapper;
		mapper = newMapper;
		return oldMapper;
	}


	public GUIPiece()
	{
		this(null);
	}

	public GUIPiece(Piece p)
	{
		this(p, false);
	}

	public GUIPiece(Piece p, boolean rotateable)
	{
		this.image = this.mapper.map(p);
		this.piece = p;
		this.rotateable = rotateable;
		//this.addMouseListener(this);

		setImageRotation();
	}

	public void paint(Graphics g)
	{
		int size = Math.min(this.getWidth(), this.getHeight());

		if (this.image != null) {
			((Graphics2D) g).drawImage(this.image, transform(size), null);
		} else {
			g.drawRect(1, 1, size - 2, size - 2);
		}
	}

	public void rotateAntiClockWise()
	{
		if (this.rotateable) {
			rotateImage(-1);
			this.piece.rotate(3);
			this.revalidate();
		}
	}

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
