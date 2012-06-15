package villebasse.ui.swing;

import java.util.EventListener;

/**
 * Rajapinta käyttäjän aiheuttamien tapahtumien kuuntelemiseksi.
 */
public interface UserEventListener extends EventListener
{
	public void userEventOccurred(UserEvent ue);
}
