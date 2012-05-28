package villebasse.ui;

public interface UI
{
  /**
   * Initializes the UI instance
   *
   * @param args Arguments to be passed to the implementing class
   *
   * @return True if successful. False otherwise.
   */
  public boolean initialize(String[] args);

  /**
   * Runs the initialized UI instance
   */
  public void run();
}
