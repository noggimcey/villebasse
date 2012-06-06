package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import villebasse.gamelogic.*;
import villebasse.ui.UI;
import villebasse.ui.swing.*;

import java.awt.image.BufferedImage;


public class UISwing implements UI
{
	private JFrame mainwin;
	private Deck deck;
	private Board board;


	public boolean initialize(String args[])
	{
		try {
			GUIPiece.setMapper(new ScannedMapper());
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

		this.deck = new DefaultDeckWithoutRoads();
		try {
			this.board = new Board(this.deck);
		} catch (Exception e) {
			System.err.println(e);
		}

		return true;
	}


	public void run()
	{
		this.mainwin = new MainWindow(this.board, this.deck);
	}


	private class BoardPanel extends JPanelWithCustomEvents //implements ComponentListener
		implements MouseListener
	{
		private Board board;
		private int height, width;

		public BoardPanel(Board board)
		{
			this.board = board;
			this.render();
			//this.addComponentListener(this);
			this.addMouseListener(this);
			//this.addBoardEventListener(listener);
		}

		private void render()
		{
			Piece[][] boardarray = this.board.asArray();
			this.height = boardarray.length + 2;
			this.width = boardarray[0].length + 2;

			this.setLayout(new GridLayout(this.height, this.width, 0, 0));

			// Header
			for (int i = 0; i < this.width; ++i)
				this.add(new GUIPiece());

			for (Piece[] row : boardarray) {
				this.add(new GUIPiece());

				for (Piece piece : row)
					this.add(new GUIPiece(piece));

				this.add(new GUIPiece());
			}

			// Footer
			for (int i = 0; i < this.width; ++i)
				this.add(new GUIPiece());
		}

		public void componentResized(ComponentEvent e)
		{
			if (e.getSource() != this)
				this.setSize(this.getPreferredSize());
			this.repaint();
		}

		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}


		public Dimension getPreferredSize()
		{
			Dimension d = this.getSize();
			int size = Math.min(d.width, d.height);
			return new Dimension(size, size);
		}

		public void mouseReleased(MouseEvent e)
		{
			System.err.println(e);
			if (e.getButton() != MouseEvent.BUTTON1) {
				this.dispatchEvent(new BoardEvent(this, 1));
				return;
			}

			int pieceHeight = this.getHeight() / this.height;
			int pieceWidth = this.getWidth() / this.width;
			int x = e.getX() / pieceWidth - 1;
			int y = e.getY() / pieceHeight - 1;

			this.dispatchEvent(new BoardEvent(this, x, y, 0));
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	}


	private class MainWindow extends JFrame //implements ComponentListener
		implements BoardEventListener
	{
		private Board board;
		private BoardPanel boardPanel;
		private Deck deck;
		private Piece piece;

		public MainWindow(Board board, Deck deck)
		{
			this.board = board;
			this.deck = deck;
			//this.setLayout(new GridLayout(4, 4, 0, 0));
			//this.addComponentListener(this);

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setPreferredSize(new Dimension(400, 400));

			replaceBoardPanel();
			drawPiece();
			this.pack();
			this.setVisible(true);
		}

		public void boardEventOccurred(BoardEvent be)
		{
			int x = be.x;
			int y = be.y;

			System.err.println("boardEventOccurred: " + x + "," + y);

			if (be.getID() == 1) {
				this.piece.rotate();
				return;
			}

			try {
				this.board.putPieceRelative(x, y, this.piece);
				replaceBoardPanel();
				drawPiece();
			} catch (Exception e) {
				System.err.println(e);
			}
		}

		private void drawPiece()
		{
			try {
				this.piece = this.deck.draw();
				System.out.println(this.piece);
			} catch (Exception e) {
				System.err.println(e);
			}
		}

		private void replaceBoardPanel()
		{
			BoardPanel newBoardPanel = new BoardPanel(this.board);
			newBoardPanel.addBoardEventListener((BoardEventListener) this);

			if (this.boardPanel != null)
				this.remove(this.boardPanel);
			this.add(newBoardPanel);

			this.boardPanel = newBoardPanel;
			this.validate();
		}
	}


/*
	private class HoverPiece extends JPanel implements MouseListener, MouseMotionListener
	{
		private int x, y;
		GUIPiece p;

		public HoverPiece(GUIPiece p)
		{
			this.p = p;
		}

    public void mouseClicked( MouseEvent e) {}
    public void mouseDragged( MouseEvent e) {}
    public void mouseEntered( MouseEvent e) {}
    public void mouseExited(  MouseEvent e) {}
    public void mousePressed( MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(   MouseEvent e)
		{
      repaint();
      x = e.getX();
      y = e.getY();
      repaint();
    }

    public void paintComponent (Graphics g)
     {
      super.paintComponent(g);
      g.drawImage(p.image, x, y, p.image.getWidth(this), p.image.getHeight(this), this);
     }
	}
*/
}
