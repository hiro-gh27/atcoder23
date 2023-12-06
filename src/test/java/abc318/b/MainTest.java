package abc318.b;

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
            "0 5 1 3" + System.lineSeparator() +
            "1 4 0 5" + System.lineSeparator() +
            "2 5 2 4";
    String output =
        "20";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2" + System.lineSeparator() +
            "0 100 0 100" + System.lineSeparator() +
            "0 100 0 100";
    String output =
        "10000";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "3" + System.lineSeparator() +
            "0 1 0 1" + System.lineSeparator() +
            "0 3 0 5" + System.lineSeparator() +
            "5 10 0 10";
    String output =
        "65";

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
