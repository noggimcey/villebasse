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


public class BoardPanel extends JPanelWithCustomEvents
	implements MouseListener, ComponentListener, MouseMotionListener
{
	private BoardGrid boardGrid;
	private GUIPiece nextPiece;
	private int mouseX, mouseY;
	private boolean mouseIn = false;

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
		if (e.getButton() != MouseEvent.BUTTON1) {
			this.dispatchEvent(new BoardEvent(this, 1));
			this.repaint();
			return;
		}

		Dimension bs = this.boardGrid.getPreferredSize();
		Insets b = ((EmptyBorder) this.getBorder()).getBorderInsets();

		int x = e.getX() - b.left;
		int y = e.getY() - b.top;

		if (x < 0 || x >= bs.width || y < 0 || y >= bs.height)
			return;

		double ps = this.boardGrid.getPieceSize();
		x = (int)(x / ps) - 1;
		y = (int)(y / ps) - 1;
		this.dispatchEvent(new BoardEvent(this, x, y, 0));
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

	public void paint(Graphics g)
	{
		super.paint(g);

		if (!this.mouseIn || this.nextPiece == null)
			return;

		double ps = this.boardGrid.getPieceSize();
		int ips = (int)(ps * 0.95);
		this.nextPiece.setSize(new Dimension(ips, ips));
		this.nextPiece.getTransform();

		((Graphics2D) g).translate(this.mouseX - ps / 2, this.mouseY - ps / 2);
		this.nextPiece.paint(g);
	}

	public GUIPiece setNextPiece(GUIPiece piece)
	{
		GUIPiece oldPiece = this.nextPiece;
		this.nextPiece = piece;
		return oldPiece;
	}


	private class BoardGrid extends JPanelWithCustomEvents
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
