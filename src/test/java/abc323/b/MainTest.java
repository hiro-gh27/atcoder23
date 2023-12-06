package abc323.b;

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
            "-xx" + System.lineSeparator() +
            "o-x" + System.lineSeparator() +
            "oo-";
    String output =
        "3 2 1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "7" + System.lineSeparator() +
            "-oxoxox" + System.lineSeparator() +
            "x-xxxox" + System.lineSeparator() +
            "oo-xoox" + System.lineSeparator() +
            "xoo-ooo" + System.lineSeparator() +
            "ooxx-ox" + System.lineSeparator() +
            "xxxxx-x" + System.lineSeparator() +
            "oooxoo-";
    String output =
        "4 7 3 1 5 2 6";

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

