package abc322.e;

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
        "4 3 5" + System.lineSeparator() +
            "5 3 0 2" + System.lineSeparator() +
            "3 1 2 3" + System.lineSeparator() +
            "3 2 4 0" + System.lineSeparator() +
            "1 0 1 4";
    String output =
        "9";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "7 3 5" + System.lineSeparator() +
            "85 1 0 1" + System.lineSeparator() +
            "37 1 1 0" + System.lineSeparator() +
            "38 2 0 0" + System.lineSeparator() +
            "45 0 2 2" + System.lineSeparator() +
            "67 1 1 0" + System.lineSeparator() +
            "12 2 2 0" + System.lineSeparator() +
            "94 2 2 1";
    String output =
        "-1";

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


