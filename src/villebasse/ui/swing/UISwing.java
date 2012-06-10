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

/**
 * Graafinen käyttöliittymä.
 */

public class UISwing
	implements UI
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


	private class MainWindow extends JFrame //implements ComponentListener
		implements BoardEventListener
	{
		private Board board;
		private BoardPanel boardPanel;
		private ControlPanel controlPanel;
		private Deck deck;
		private Piece piece;

		public MainWindow(Board board, Deck deck)
		{
			this.board = board;
			this.deck = deck;

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setPreferredSize(new Dimension(400, 400));
			//new BoxLayout(this, BoxLayout.Y_AXIS);

			this.boardPanel = new BoardPanel(board);
			this.boardPanel.addBoardEventListener((BoardEventListener) this);
			this.add(this.boardPanel);

			this.controlPanel = new ControlPanel();
			//this.add(this.controlPanel);

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
				this.boardPanel.update();
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
				this.piece = null;
				System.err.println(e);
			}
		}
	}

	private class ControlPanel extends JPanel
	{
		public ControlPanel()
		{
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
