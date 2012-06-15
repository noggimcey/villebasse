package villebasse.ui;

import java.lang.Runnable;

/**
 * Käyttöliittymien yhteinen rajapinta.
 */
public interface UI extends Runnable
{
	/**
	 * Alusta käyttöliittymäinstanssi.
	 *
	 * @param args  Käyttöliittymän alustusparametrit
	 * @return Onnistuiko alustus
	 */
	public boolean initialize(String[] args);
}
