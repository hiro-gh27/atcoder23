package abc314.d;

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
        "7" + System.lineSeparator() +
            "AtCoder" + System.lineSeparator() +
            "5" + System.lineSeparator() +
            "1 4 i" + System.lineSeparator() +
            "3 0 a" + System.lineSeparator() +
            "1 5 b" + System.lineSeparator() +
            "2 0 a" + System.lineSeparator() +
            "1 4 Y";
    String output =
        "atcYber";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "35" + System.lineSeparator() +
            "TheQuickBrownFoxJumpsOverTheLazyDog" + System.lineSeparator() +
            "10" + System.lineSeparator() +
            "2 0 a" + System.lineSeparator() +
            "1 19 G" + System.lineSeparator() +
            "1 13 m" + System.lineSeparator() +
            "1 2 E" + System.lineSeparator() +
            "1 21 F" + System.lineSeparator() +
            "2 0 a" + System.lineSeparator() +
            "1 27 b" + System.lineSeparator() +
            "3 0 a" + System.lineSeparator() +
            "3 0 a" + System.lineSeparator() +
            "1 15 i";
    String output =
        "TEEQUICKBROWMFiXJUGPFOVERTBELAZYDOG";

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
