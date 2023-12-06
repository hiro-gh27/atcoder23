package abc327.c;
import static org.hamcrest.CoreMatchers.is;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void 入力例_1() throws Exception {
    String input =
        "1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
            "4 5 6 7 8 9 1 2 3" + System.lineSeparator() +
            "7 8 9 1 2 3 4 5 6" + System.lineSeparator() +
            "2 3 4 5 6 7 8 9 1" + System.lineSeparator() +
            "5 6 7 8 9 1 2 3 4" + System.lineSeparator() +
            "8 9 1 2 3 4 5 6 7" + System.lineSeparator() +
            "3 4 5 6 7 8 9 1 2" + System.lineSeparator() +
            "6 7 8 9 1 2 3 4 5" + System.lineSeparator() +
            "9 1 2 3 4 5 6 7 8";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
            "2 3 4 5 6 7 8 9 1" + System.lineSeparator() +
            "3 4 5 6 7 8 9 1 2" + System.lineSeparator() +
            "4 5 6 7 8 9 1 2 3" + System.lineSeparator() +
            "5 6 7 8 9 1 2 3 4" + System.lineSeparator() +
            "6 7 8 9 1 2 3 4 5" + System.lineSeparator() +
            "7 8 9 1 2 3 4 5 6" + System.lineSeparator() +
            "8 9 1 2 3 4 5 6 7" + System.lineSeparator() +
            "9 1 2 3 4 5 6 7 8";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
            "4 5 6 7 8 9 1 2 3" + System.lineSeparator() +
            "7 8 9 1 2 3 4 5 6" + System.lineSeparator() +
            "1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
            "4 5 6 7 8 9 1 2 3" + System.lineSeparator() +
            "7 8 9 1 2 3 4 5 6" + System.lineSeparator() +
            "1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
            "4 5 6 7 8 9 1 2 3" + System.lineSeparator() +
            "7 8 9 1 2 3 4 5 6";
    String output =
        "No";

    assertIO(input, output);
  }

  private void assertIO(String input, String output) throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    Main.main(new String[0]);

    Assert.assertThat(out.toString(), is(output + System.lineSeparator()));
  }
}
