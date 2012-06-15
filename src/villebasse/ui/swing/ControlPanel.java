package villebasse.ui.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ControlPanel extends JPanelUserEventSource
{
	private JTextArea textArea;

	public ControlPanel()
	{
		this.setLayout(new BorderLayout());

		this.textArea = new JTextArea(6, 80);
		this.textArea.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret) this.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		this.add(new JScrollPane(this.textArea), BorderLayout.CENTER);

		this.add(new JButton("new piece"), BorderLayout.EAST);
	}

	public void putText(String text)
	{
		this.textArea.append(text);
	}
}
