package villebasse;

import java.util.Arrays;
import villebasse.ui.*;


public class VilleBasse
{
	public static void main(String args[]) throws Exception
	{
		UI ui = null;

		int i;
		for (i = 0; i < args.length && args[i].charAt(0) == '-'; ++i) {
			if (args[i].equals("-gui")) {
				ui = new UISwing();
			} else {
				System.err.println(args[i] + ": Unknown option");
			}
		}

		if (ui == null)
			ui = new UIConsole();

		if (ui.initialize(Arrays.copyOfRange(args, i, args.length)) != 0)
			System.exit(1);
		ui.run();
	}
}
