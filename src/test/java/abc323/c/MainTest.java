package abc323.c;


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
        "3 4" + System.lineSeparator() +
            "1000 500 700 2000" + System.lineSeparator() +
            "xxxo" + System.lineSeparator() +
            "ooxx" + System.lineSeparator() +
            "oxox";
    String output =
        "0" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "5 5" + System.lineSeparator() +
            "1000 1500 2000 2000 2500" + System.lineSeparator() +
            "xxxxx" + System.lineSeparator() +
            "oxxxx" + System.lineSeparator() +
            "xxxxx" + System.lineSeparator() +
            "oxxxx" + System.lineSeparator() +
            "oxxxx";
    String output =
        "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "7 8" + System.lineSeparator() +
            "500 500 500 500 500 500 500 500" + System.lineSeparator() +
            "xxxxxxxx" + System.lineSeparator() +
            "oxxxxxxx" + System.lineSeparator() +
            "ooxxxxxx" + System.lineSeparator() +
            "oooxxxxx" + System.lineSeparator() +
            "ooooxxxx" + System.lineSeparator() +
            "oooooxxx" + System.lineSeparator() +
            "ooooooxx";
    String output =
        "7" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "5" + System.lineSeparator() +
            "4" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2" + System.lineSeparator() +
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
