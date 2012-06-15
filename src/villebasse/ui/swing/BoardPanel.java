package villebasse.ui.swing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import villebasse.gamelogic.*;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Piirtää pelilaudan ja seuraa hiiren tapahtumia.
 *
 * Toimittaa laudalla tapahtuvat painallukset niitä kuunteleville.
 */
public class BoardPanel extends JPanelUserEventSource
	implements MouseListener, ComponentListener, MouseMotionListener
{
	private BoardGrid boardGrid;
	private GUIPiece nextPiece;
	private int mouseX, mouseY;
	private boolean mouseIn = false;

	/**
	 * Konstruktori.
	 *
	 * @param board  Lauta, jota halutaan piirrettävän
	 */
	public BoardPanel(Board board)
	{
		this.boardGrid = new BoardGrid(board);
		this.addComponentListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setLayout(new BorderLayout());
		this.add(this.boardGrid, BorderLayout.CENTER);
		this.update();
	}

	/**
	 * Päivitä lauta.
	 */
	public void update()
	{
		this.boardGrid.update();
		this.pad();
		this.validate();
	}

	/* ComponentListener */
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

	public void componentResized(ComponentEvent e)
	{
		this.pad();
	}

	/* MouseListener */
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	public void mouseEntered(MouseEvent e)
	{
		this.mouseIn = true;
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		this.repaint();
	}

	public void mouseExited(MouseEvent e)
	{
		this.mouseIn = false;
		this.repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		this.repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		Insets b = ((EmptyBorder) this.getBorder()).getBorderInsets();
		int relX = e.getX() - b.left;
		int relY = e.getY() - b.top;
		double ps = this.boardGrid.getPieceSize();

		int x = (int)(relX / ps) - 1;
		int y = (int)(relY / ps) - 1;

		double dx = (relX - (x + 1) * ps) / ps;
		double dy = (relY - (y + 1) * ps) / ps;

		if (e.getButton() != MouseEvent.BUTTON1) {
			if (this.nextPiece != null) {
				this.nextPiece.rotateClockWise();
				this.repaint();
			} else {
				this.dispatchEvent(
					new BoardClickEvent(this, x, y, dx, dy, BoardClickEvent.BUTTON2)
				);
			}
			return;
		}

		this.dispatchEvent(
			new BoardClickEvent(this, x, y, dx, dy, BoardClickEvent.BUTTON1)
		);
	}

	/**
	 * Piirrä komponentti.
	 *
	 * @param g  Alusta, jolle komponentti piirretään
	 */
	public void paint(Graphics g)
	{
		super.paint(g);

		if (!this.mouseIn || this.nextPiece == null)
			return;

		double ps = this.boardGrid.getPieceSize();
		int ips = (int)(ps * 0.95);
		this.nextPiece.setSize(new Dimension(ips, ips));

		((Graphics2D) g).translate(this.mouseX - ps / 2, this.mouseY - ps / 2);
		this.nextPiece.paint(g);
	}

	/**
	 * Aseta seuraavaksi asetettava pala.
	 *
	 * @param piece  Pala
	 */
	public void setNextPiece(Piece piece)
	{
		GUIPiece guip = null;
		if (piece != null)
			guip = new GUIPiece(piece, true);

		this.nextPiece = guip;
		this.update();
	}

	private void pad()
	{
		Dimension cs = this.getSize();
		Dimension bs = this.boardGrid.getPreferredSize(cs);

		int vert = cs.height - bs.height;
		int horz = cs.width - bs.width;

		this.setBorder(
			new EmptyBorder(vert / 2, horz / 2, (vert + 1) / 2, (horz + 1) / 2)
		);
	}

	/**
	 * Todellinen laudan piirtäjä.
	 */
	private class BoardGrid extends JPanel
	{
		public int height, width;
		private Board board;


		public BoardGrid(Board board)
		{
			this.board = board;
			this.update();
		}

		public void update()
		{
			Piece[][] boardArray = board.asArray();
			this.height = boardArray.length + 2;
			this.width = boardArray[0].length + 2;

			this.removeAll();
			this.setLayout(new GridLayout(this.height, this.width, 0, 0));
			this.addPieces(boardArray);
		}

		public Dimension getPreferredSize()
		{
			return getPreferredSize(this.getSize());
		}

		public Dimension getPreferredSize(Dimension d)
		{
			double ps = this.getPieceSize(d);
			return new Dimension((int)(this.width * ps), (int)(this.height * ps));
		}

		public double getPieceSize()
		{
			return getPieceSize(this.getSize());
		}

		public double getPieceSize(Dimension d)
		{
			double h = d.height / this.height;
			double w = d.width / this.width;
			return Math.min(h, w);
		}

		private void addPieces(Piece[][] boardArray)
		{
			// Header
			for (int i = 0; i < this.width; ++i)
				this.add(new GUIPiece());

			for (Piece[] row : boardArray) {
				this.add(new GUIPiece());

				for (Piece piece : row)
					this.add(new GUIPiece(piece));

				this.add(new GUIPiece());
			}

			// Footer
			for (int i = 0; i < this.width; ++i)
				this.add(new GUIPiece());
		}
	}
}
