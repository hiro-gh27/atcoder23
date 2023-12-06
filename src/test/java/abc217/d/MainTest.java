package abc217.d;

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
        "5 3" + System.lineSeparator() +
            "2 2" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "2 2";
    String output =
        "5" + System.lineSeparator() +
            "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "5 3" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "1 4" + System.lineSeparator() +
            "2 3";
    String output =
        "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "100 10" + System.lineSeparator() +
            "1 31" + System.lineSeparator() +
            "2 41" + System.lineSeparator() +
            "1 59" + System.lineSeparator() +
            "2 26" + System.lineSeparator() +
            "1 53" + System.lineSeparator() +
            "2 58" + System.lineSeparator() +
            "1 97" + System.lineSeparator() +
            "2 93" + System.lineSeparator() +
            "1 23" + System.lineSeparator() +
            "2 84";
    String output =
        "69" + System.lineSeparator() +
            "31" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "38" + System.lineSeparator() +
            "38";

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
