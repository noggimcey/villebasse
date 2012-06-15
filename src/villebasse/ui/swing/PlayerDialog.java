package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import villebasse.gamelogic.Player;

/**
 * Dialogi pelajien luomiseksi.
 */
public class PlayerDialog extends JDialog
	implements ActionListener
{
	private PlayerPanel playerPanel;
	private Controls controls;

	/**
	 * Uusi dialogi.
	 *
	 * @param owner  Dialogin omistaja
	 */
	public PlayerDialog(Frame owner)
	{
		super(owner, "Add players...", true);

		this.playerPanel = new PlayerPanel();
		this.controls = new Controls();

		this.setLayout(new BorderLayout());
		this.add(this.playerPanel, BorderLayout.CENTER);
		this.add(this.controls, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Lisää pelaaja.
	 *
	 * @return Onnistuiko lisäys
	 */
	public boolean addPlayer()
	{
		return this.playerPanel.addPlayer();
	}

	/**
	 * Lista pelaajista.
	 *
	 * @return Lista pelaajista
	 */
	public Player[] players()
	{
		return this.playerPanel.players();
	}

	/* ActionListener */
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();

		if (cmd.equals("add"))
			this.playerPanel.addPlayer();
		else if (cmd.equals("remove"))
			this.playerPanel.removePlayer();
		else if (cmd.equals("done"))
			this.dispose();

		this.validate();
	}


	/**
	 * Näytä dialogi ja palauta lista annetuista pelaajista.
	 *
	 * @param owner  Dialogin omistaja
	 * @param title  Dialogin otsikkoteksti
	 * @return Lista pelaajista
	 */
	public static Player[] showDialog(Frame owner, String title)
	{
		PlayerDialog dialog = new PlayerDialog(owner);
		return dialog.players();
	}


	/**
	 * Värivalitsin ja -näytin.
	 */
	private class ColorSelector extends JPanel
		implements MouseListener
	{
		private Color color;
		private boolean pressed;

		public ColorSelector()
		{
			this(Color.gray);
		}

		public ColorSelector(Color color)
		{
			this.color = color;
			this.addMouseListener(this);
		}

		public Color getColor()
		{
			return this.color;
		}

		public void paint(Graphics g)
		{
			Dimension d = this.getSize();
			g.setColor(this.color);
			g.fill3DRect(1, 1, d.width - 2, d.height - 2, !this.pressed);
		}

		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited( MouseEvent e) {}

		public void mousePressed(MouseEvent e)
		{
			this.pressed = true;
			this.repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			this.pressed = false;

			Color newColor = null;
			try {
				newColor = JColorChooser.showDialog(this, "Pick a color", this.color);
			} catch (HeadlessException he) {
				// shouldn't be possible in mouse handler
				System.err.println(he);
			}

			if (newColor != null)
				this.color = newColor;

			this.repaint();
		}
	}

	/**
	 * Napit kenttien käsittelemiseksi.
	 */
	private class Controls extends JPanel
	{
		public Controls()
		{
			JButton removeBtn = new JButton("-");
			JButton addBtn = new JButton("+");
			JButton doneBtn = new JButton("done");

			removeBtn.setActionCommand("remove");
			addBtn.setActionCommand("add");
			doneBtn.setActionCommand("done");

			removeBtn.addActionListener(PlayerDialog.this);
			addBtn.addActionListener(PlayerDialog.this);
			doneBtn.addActionListener(PlayerDialog.this);

			this.add(removeBtn);
			this.add(addBtn);
			this.add(doneBtn);
		}
	}

	/**
	 * Pelaajaa vastaava kenttä.
	 */
	private class PlayerEntry extends JPanel
	{
		private ColorSelector colorSelector;
		private JTextField textField;

		public PlayerEntry()
		{
			this.colorSelector = new ColorSelector();
			this.textField = new JTextField(20);

			this.setLayout(new BorderLayout());
			this.add(this.textField, BorderLayout.CENTER);
			this.add(this.colorSelector, BorderLayout.EAST);
		}

		public Player getPlayer()
		{
			return new Player(textField.getText(), colorSelector.getColor());
		}
	}

	/**
	 * Pelaajat.
	 */
	private class PlayerPanel extends JPanel
	{
		public PlayerPanel()
		{
			this(1);
		}

		public PlayerPanel(int numberOfEntries)
		{
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			while (numberOfEntries-- > 0)
				this.addPlayer();
		}

		public boolean addPlayer()
		{
			this.add(new PlayerEntry());
			this.validate();

			return true;
		}

		public Player[] players()
		{
			Player[] pl = new Player[this.getComponentCount()];

			int i = 0;
			for (Component c : this.getComponents())
				pl[i++] = ((PlayerEntry) c).getPlayer();

			return pl;
		}

		public void removePlayer()
		{
			Component[] comps = this.getComponents();
			this.remove(comps[comps.length - 1]);

			if (comps.length == 1)
				this.addPlayer();

			this.validate();
		}
	}
}
