import java.io.IOException;

/**
 * Mock Appendable object used strictly for testing.
 */
public class MockAppendable implements Appendable{
  /**
   * Automatically throws an IO exception.
   * @param csq
   *         The character sequence to append.  If {@code csq} is
   *         {@code null}, then the four characters {@code "null"} are
   *         appended to this Appendable.
   *
   * @return IO Exception
   * @throws IOException always
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("IO");
  }

  /**
   * Automatically throws an IO exception.
   * @param csq
   *         The character sequence from which a subsequence will be
   *         appended.  If {@code csq} is {@code null}, then characters
   *         will be appended as if {@code csq} contained the four
   *         characters {@code "null"}.
   *
   * @param start
   *         The index of the first character in the subsequence
   *
   * @param end
   *         The index of the character following the last character in the
   *         subsequence
   *
   * @return IO Exception
   * @throws IOException always
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("IO");
  }

  /**
   * Automatically throws an IO exception.
   * @param c
   *         The character to append
   *
   * @return IO Exception
   * @throws IOException always
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("IO");
  }
}
