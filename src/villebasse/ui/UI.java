package villebasse.ui;

public interface UI
{
  /**
   * Initializes the UI instance
   *
   * @param args Arguments to be passed to the implementing class
   *
   * @return 0 if successful. Non-zero otherwise.
   */
  public int initialize(String[] args);

  /**
   * Runs the initialized UI instance
   */
  public void run();
}
