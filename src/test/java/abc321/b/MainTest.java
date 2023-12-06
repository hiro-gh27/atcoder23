package abc321.b;

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
        "5 180" + System.lineSeparator() +
            "40 60 80 50";
    String output =
        "70";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3 100" + System.lineSeparator() +
            "100 100";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "5 200" + System.lineSeparator() +
            "0 0 99 99";
    String output =
        "-1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "10 480" + System.lineSeparator() +
            "59 98 88 54 70 24 8 94 46";
    String output =
        "45";

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
