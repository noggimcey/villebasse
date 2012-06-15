package villebasse.ui.swing;

import java.awt.*;
import javax.swing.*;

public class ControlPanel extends JPanelUserEventSource
{
	public ControlPanel()
	{
		this.setLayout(new BorderLayout());
		this.add(new JButton("Push me"), BorderLayout.WEST);
		this.add(new JTextArea("Welcome!", 4, 80), BorderLayout.CENTER);
		this.add(new JButton("and then just touch me"), BorderLayout.EAST);
	}
}


