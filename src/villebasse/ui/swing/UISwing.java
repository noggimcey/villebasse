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
	private VilleBasseEngine engine;

	public boolean initialize(String args[])
	{
		try {
			GUIPiece.setMapper(new ScannedMapper());
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

		this.engine = new VilleBasseEngine();

		return true;
	}


	public void run()
	{
		this.mainwin = new MainWindow(this.engine);
	}


	private class MainWindow extends JFrame //implements ComponentListener
		implements BoardEventListener
	{
		private BoardPanel boardPanel;
		private ControlPanel controlPanel;
		private VilleBasseEngine engine;

		public MainWindow(VilleBasseEngine engine)
		{
			this.engine = engine;

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setPreferredSize(new Dimension(400, 400));
			//new BoxLayout(this, BoxLayout.Y_AXIS);

			this.boardPanel = new BoardPanel(this.engine.getBoard());
			this.boardPanel.addBoardEventListener((BoardEventListener) this);
			this.add(this.boardPanel);

			this.controlPanel = new ControlPanel();
			//this.add(this.controlPanel);

			this.engine.addPlayer("foo", Color.pink);
			this.engine.addPlayer("foo", Color.gray);

			this.engine.startGame();
			this.boardPanel.setNextPiece(new GUIPiece(this.engine.getCurrentPiece(), true));
			this.pack();
			this.setVisible(true);
		}

		public void boardEventOccurred(BoardEvent be)
		{
			if (this.engine.getState() == VilleBasseEngine.EngineState.INGAME_PUT_PIECE) {
				int x = be.x;
				int y = be.y;

				if (!this.engine.putPiece(x, y))
					return;

				this.boardPanel.setNextPiece(null);
			} else if (this.engine.getState() == VilleBasseEngine.EngineState.INGAME_PLACE_MEEPLE) {
				if (be.getID() == 0) {
					double dx = be.dx;
					double dy = be.dy;
					this.engine.placeMeeple(dx, dy);
					System.err.println(dx + "  " + dy);
				}
				this.nextTurn();
			}
			this.boardPanel.update();
		}

		private void nextTurn()
		{
			if (!this.engine.nextTurn())
				return;
			this.boardPanel.setNextPiece(new GUIPiece(this.engine.getCurrentPiece(), true));
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
