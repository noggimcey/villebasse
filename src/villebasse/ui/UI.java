package villebasse.ui;

/**
 * Käyttöliittymien yhteinen rajapinta.
 */

public interface UI
{
	/**
	 * Alusta käyttöliittymäinstanssi.
	 *
	 * @param args  Käyttöliittymän alustusparametrit
	 *
	 * @return Onnistuiko alustus
	 */
	public boolean initialize(String[] args);

	/**
	 * Aja alustettua käyttöliittymäinstanssia.
	 */
	public void run();
}
