package abc121.b;


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
        "2 3 -10" + System.lineSeparator() +
            "1 2 3" + System.lineSeparator() +
            "3 2 1" + System.lineSeparator() +
            "1 2 2";
    String output =
        "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "5 2 -4" + System.lineSeparator() +
            "-2 5" + System.lineSeparator() +
            "100 41" + System.lineSeparator() +
            "100 40" + System.lineSeparator() +
            "-3 0" + System.lineSeparator() +
            "-6 -2" + System.lineSeparator() +
            "18 -13";
    String output =
        "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "3 3 0" + System.lineSeparator() +
            "100 -100 0" + System.lineSeparator() +
            "0 100 100" + System.lineSeparator() +
            "100 100 100" + System.lineSeparator() +
            "-100 100 100";
    String output =
        "0";

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
