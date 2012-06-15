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

public class UISwing extends JFrame
	implements UI, GameStateEventListener, ControlPanelEventListener
{
	private BoardPanel boardPanel;
	private ControlPanel controlPanel;
	private VilleBasseEngine engine;

	public boolean initialize(String args[])
	{
		try {
			GUIPiece.setMapper(new DrawnMapper());
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

		this.engine = new VilleBasseEngine();
		this.engine.addGameStateEventListener(this);

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(512, 512));

		this.boardPanel = new BoardPanel(this.engine.getBoard());
		this.controlPanel = new ControlPanel();

		this.add(this.boardPanel, BorderLayout.CENTER);
		this.add(this.controlPanel, BorderLayout.SOUTH);

		this.controlPanel.addUserEventListener((ControlPanelEventListener) this);

		this.pack();

		return true;
	}

	public void run()
	{
		this.setVisible(true);

		for (Player p : PlayerDialog.showDialog(this, "Add Players..."))
			this.engine.addPlayer(p);

		this.engine.startGame();
	}


	public void gameStateGameEnd(GameStateEvent gse) {}
	public void gameStateGameStart(GameStateEvent gse) {}
	public void gameStateRoundStart(GameStateEvent gse) {}

	public void gameStatePlaceMeeple(GameStateEvent gse)
	{
		this.updatePiece(null);
		this.boardPanel.setUserEventListener(new PlaceMeepleListener());
	}

	public void gameStateRemoveMeeples(GameStateEvent gse)
	{
		this.updatePiece(null);
		this.boardPanel.setUserEventListener(new RemoveMeeplesListener());
	}

	public void gameStateTurnStart(GameStateEvent gse)
	{
		System.err.println("Now in turn: " + this.engine.getCurrentPlayer().getName());
		this.updatePiece(this.engine.getCurrentPiece());
		this.boardPanel.setUserEventListener(new PutPieceListener());
	}

	public void controlPanelEventOccurred(ControlPanelEvent cpe)
	{
		System.err.println(cpe);
	}

	public void userEventOccurred(UserEvent ue) {}


	private void updatePiece(Piece p)
	{
		if (p == null)
			this.boardPanel.setNextPiece(null);
		else
			this.boardPanel.setNextPiece(new GUIPiece(p, true));
		this.boardPanel.update();
	}


	private class PutPieceListener
		implements BoardClickEventListener
	{
		public void userEventOccurred(UserEvent ue) {}

		public void boardClickEventOccurred(BoardClickEvent be)
		{
			UISwing.this.engine.putPiece(be.x, be.y);
		}
	}

	private class PlaceMeepleListener
		implements BoardClickEventListener
	{
		public void userEventOccurred(UserEvent ue) {}

		public void boardClickEventOccurred(BoardClickEvent be)
		{
			if (be.button == BoardClickEvent.BUTTON1)
				UISwing.this.engine.placeMeeple(be.x, be.y, be.dx, be.dy);
			else
				UISwing.this.engine.placeMeeple();
		}
	}

	private class RemoveMeeplesListener
		implements BoardClickEventListener
	{
		public void userEventOccurred(UserEvent ue) {}

		public void boardClickEventOccurred(BoardClickEvent be)
		{
			if (be.button == BoardClickEvent.BUTTON2)
				UISwing.this.engine.nextTurn();
			else {
				UISwing.this.engine.removeMeeple(be.x, be.y);
				UISwing.this.boardPanel.update();
			}
		}
	}
}
