package villebasse.ui.swing;

/**
 * Rajapinta laudalla tapahtuvien painallusten kuuntelemiseksi.
 *
 * Laajentaa yleistä käyttäjän aiheuttaman tapahtuman kuuntelijaa
 */
public interface BoardClickEventListener extends UserEventListener
{
	public void boardClickEventOccurred(BoardClickEvent bce);
}
