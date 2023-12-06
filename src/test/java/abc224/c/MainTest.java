package abc224.c;

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
        "4" + System.lineSeparator() +
            "0 1" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "-1 -1";
    String output =
        "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "20" + System.lineSeparator() +
            "224 433" + System.lineSeparator() +
            "987654321 987654321" + System.lineSeparator() +
            "2 0" + System.lineSeparator() +
            "6 4" + System.lineSeparator() +
            "314159265 358979323" + System.lineSeparator() +
            "0 0" + System.lineSeparator() +
            "-123456789 123456789" + System.lineSeparator() +
            "-1000000000 1000000000" + System.lineSeparator() +
            "124 233" + System.lineSeparator() +
            "9 -6" + System.lineSeparator() +
            "-4 0" + System.lineSeparator() +
            "9 5" + System.lineSeparator() +
            "-7 3" + System.lineSeparator() +
            "333333333 -333333333" + System.lineSeparator() +
            "-9 -1" + System.lineSeparator() +
            "7 -10" + System.lineSeparator() +
            "-1 5" + System.lineSeparator() +
            "324 633" + System.lineSeparator() +
            "1000000000 -1000000000" + System.lineSeparator() +
            "20 0";
    String output =
        "1124";

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
