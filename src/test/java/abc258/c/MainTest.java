package abc258.c;

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
        "3 3" + System.lineSeparator() +
            "abc" + System.lineSeparator() +
            "2 2" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "2 2";
    String output =
        "b" + System.lineSeparator() +
            "a";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "10 8" + System.lineSeparator() +
            "dsuccxulnl" + System.lineSeparator() +
            "2 4" + System.lineSeparator() +
            "2 7" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "2 7" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "2 5";
    String output =
        "c" + System.lineSeparator() +
            "u" + System.lineSeparator() +
            "c" + System.lineSeparator() +
            "u";

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
