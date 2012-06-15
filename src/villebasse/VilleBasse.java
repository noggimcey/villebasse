package villebasse;

import java.util.Arrays;
import villebasse.ui.*;
import villebasse.ui.swing.UISwing;

/**
 * Pääohjelma.
 */

public class VilleBasse
{
	public static void main(String args[]) throws Exception
	{
		int i;
		for (i = 0; i < args.length && args[i].charAt(0) == '-'; ++i) {
			System.err.println(args[i] + ": Unknown option");
		}

		UI ui = new UISwing();

		if (!ui.initialize(Arrays.copyOfRange(args, i, args.length))) {
			System.err.println("UI initialization failed!");
			System.exit(1);
		}
		System.err.println("Starting UI...");
		ui.run();
	}
}
