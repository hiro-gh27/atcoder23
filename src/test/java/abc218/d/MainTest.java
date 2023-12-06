package abc218.d;

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
        "6" + System.lineSeparator() +
            "0 0" + System.lineSeparator() +
            "0 1" + System.lineSeparator() +
            "1 0" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "2 0" + System.lineSeparator() +
            "2 1";
    String output =
        "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "0 1" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "3 4";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "7" + System.lineSeparator() +
            "0 1" + System.lineSeparator() +
            "1 0" + System.lineSeparator() +
            "2 0" + System.lineSeparator() +
            "2 1" + System.lineSeparator() +
            "2 2" + System.lineSeparator() +
            "3 0" + System.lineSeparator() +
            "3 2";
    String output =
        "1";

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
