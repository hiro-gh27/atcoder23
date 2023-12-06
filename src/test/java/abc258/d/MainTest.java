package abc258.d;

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
            "3 4" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "4 2";
    String output =
        "18";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "10 1000000000" + System.lineSeparator() +
            "3 3" + System.lineSeparator() +
            "1 6" + System.lineSeparator() +
            "4 7" + System.lineSeparator() +
            "1 8" + System.lineSeparator() +
            "5 7" + System.lineSeparator() +
            "9 9" + System.lineSeparator() +
            "2 4" + System.lineSeparator() +
            "6 4" + System.lineSeparator() +
            "5 1" + System.lineSeparator() +
            "3 1";
    String output =
        "1000000076";

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
