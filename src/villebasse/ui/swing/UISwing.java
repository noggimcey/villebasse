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
	implements UI, GameStateEventListener, UserEventListener
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

		this.controlPanel.addUserEventListener(this);

		this.pack();

		return true;
	}

	public void run()
	{
		this.setVisible(true);

		this.controlPanel.putText("Add players...");

		for (Player p : PlayerDialog.showDialog(this, "Add Players..."))
			this.engine.addPlayer(p);

		this.engine.startGame();
	}

	/* GameStateEventListener */
	public void gameStateGameEnd(GameStateEvent gse)
	{
		this.boardPanel.setNextPiece(null);
		this.boardPanel.setUserEventListener(null);

		this.controlPanel.putText("\nGame ended.\n");
		for (Player p : this.engine.getPlayers())
			this.controlPanel.putText(p.getName() + " (" + p.getPoints() + " p)\n");
	}

	public void gameStateGameStart(GameStateEvent gse)
	{
		this.controlPanel.putText(" Game starts!\n");
	}

	public void gameStateRoundStart(GameStateEvent gse)
	{
		this.controlPanel.putText("\n" +
			"Round " + this.engine.getRoundNumber() + ". " +
			this.engine.piecesLeft() + " piece(s) left.\n"
		);
	}

	public void gameStatePlaceMeeple(GameStateEvent gse)
	{
		Player p = this.engine.getCurrentPlayer();

		this.controlPanel.putText(
			"You have " + p.meeplesLeft() + " meeple(s) left.\n" +
			"Place a meeple with button 1 or skip this phase with button 2.\n"
		);

		this.boardPanel.setNextPiece(null);
		this.boardPanel.setUserEventListener(new PlaceMeepleListener());
	}

	public void gameStateRemoveMeeples(GameStateEvent gse)
	{
		this.controlPanel.putText(
			"Remove meeples with button 1 and continue to next turn with button 2.\n"
		);

		this.boardPanel.setNextPiece(null);
		this.boardPanel.setUserEventListener(new RemoveMeeplesListener());
	}

	public void gameStateTurnStart(GameStateEvent gse)
	{
		this.boardPanel.setNextPiece(this.engine.getCurrentPiece());

		Player p = this.engine.getCurrentPlayer();
		this.controlPanel.putText("\n" +
			"Now in turn: " + p.getName() + " (" + p.getPoints() + " p)\n" +
			"Place the piece with button 1 and rotate it with button 2.\n"
		);

		this.boardPanel.setUserEventListener(new PutPieceListener());
	}

	/* UserEventListener */
	public void userEventOccurred(UserEvent ue)
	{
		if (ue.command.equals("replace piece")) {
			this.engine.replaceCurrentPiece();
			this.boardPanel.setNextPiece(this.engine.getCurrentPiece());
		}
	}


	/* BoardClickEventListeners */
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
			else
				if (UISwing.this.engine.removeMeeple(be.x, be.y)) {
					; // TODO: ask user for points
					UISwing.this.boardPanel.update();
				}
		}
	}
}
