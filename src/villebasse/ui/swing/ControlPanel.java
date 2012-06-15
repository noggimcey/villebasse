package villebasse.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 * Pääikkunaan piirrettävä ohjauslauta, joka kertoo pelin tilasta
 * ja voi tarjota painikkeita käyttäjän paineltavaksi.
 */
public class ControlPanel extends JPanelUserEventSource
	implements ActionListener
{
	private JTextArea textArea;

	/**
	 * Konstruktori.
	 */
	public ControlPanel()
	{
		this.setLayout(new BorderLayout());

		this.textArea = new JTextArea(6, 80);
		this.textArea.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret) this.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		this.add(new JScrollPane(this.textArea), BorderLayout.CENTER);

		JButton b = new JButton("replace piece");
		b.addActionListener(this);

		this.add(b, BorderLayout.EAST);
	}

	/**
	 * Lisää tekstiä.
	 *
	 * @param text  Lisättävä teksti
	 */
	public void putText(String text)
	{
		this.textArea.append(text);
	}

	/* ActionListener */
	public void actionPerformed(ActionEvent ae)
	{
		this.dispatchEvent(new UserEvent(this, "replace piece"));
	}
}
