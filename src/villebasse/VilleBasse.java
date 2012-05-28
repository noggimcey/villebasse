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
			} else if (args[i].equals("-imagetest")) {
				ui = new UIImageTest();
			} else {
				System.err.println(args[i] + ": Unknown option");
			}
		}

		if (ui == null)
			ui = new UIConsole();

		if (!ui.initialize(Arrays.copyOfRange(args, i, args.length)))
			System.exit(1);
		System.err.println("Starting UI...");
		ui.run();
	}
}
