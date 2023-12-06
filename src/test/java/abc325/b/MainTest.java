package abc325.b;

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
            "5 0" + System.lineSeparator() +
            "3 3" + System.lineSeparator() +
            "2 18";
    String output =
        "8";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2" + System.lineSeparator() +
            "1 10" + System.lineSeparator() +
            "1000000 20";
    String output =
        "1000000";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "6" + System.lineSeparator() +
            "31 3" + System.lineSeparator() +
            "20 8" + System.lineSeparator() +
            "11 5" + System.lineSeparator() +
            "4 3" + System.lineSeparator() +
            "47 14" + System.lineSeparator() +
            "1 18";
    String output =
        "67";

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

