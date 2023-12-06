package abc306.d;
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
        "5" + System.lineSeparator() +
            "1 100" + System.lineSeparator() +
            "1 300" + System.lineSeparator() +
            "0 -200" + System.lineSeparator() +
            "1 500" + System.lineSeparator() +
            "1 300";
    String output =
        "600";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "0 -1" + System.lineSeparator() +
            "1 -2" + System.lineSeparator() +
            "0 -3" + System.lineSeparator() +
            "1 -4";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "15" + System.lineSeparator() +
            "1 900000000" + System.lineSeparator() +
            "0 600000000" + System.lineSeparator() +
            "1 -300000000" + System.lineSeparator() +
            "0 -700000000" + System.lineSeparator() +
            "1 200000000" + System.lineSeparator() +
            "1 300000000" + System.lineSeparator() +
            "0 -600000000" + System.lineSeparator() +
            "1 -900000000" + System.lineSeparator() +
            "1 600000000" + System.lineSeparator() +
            "1 -100000000" + System.lineSeparator() +
            "1 -400000000" + System.lineSeparator() +
            "0 900000000" + System.lineSeparator() +
            "0 200000000" + System.lineSeparator() +
            "1 -500000000" + System.lineSeparator() +
            "1 900000000";
    String output =
        "4100000000";

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

