package abc214.c;
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
        "3" + System.lineSeparator() +
            "4 1 5" + System.lineSeparator() +
            "3 10 100";
    String output =
        "3" + System.lineSeparator() +
            "7" + System.lineSeparator() +
            "8";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "100 100 100 100" + System.lineSeparator() +
            "1 1 1 1";
    String output =
        "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "1 2 3 4" + System.lineSeparator() +
            "1 2 4 7";
    String output =
        "1" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "4" + System.lineSeparator() +
            "7";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "8" + System.lineSeparator() +
            "84 87 78 16 94 36 87 93" + System.lineSeparator() +
            "50 22 63 28 91 60 64 27";
    String output =
        "50" + System.lineSeparator() +
            "22" + System.lineSeparator() +
            "63" + System.lineSeparator() +
            "28" + System.lineSeparator() +
            "44" + System.lineSeparator() +
            "60" + System.lineSeparator() +
            "64" + System.lineSeparator() +
            "27";

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
