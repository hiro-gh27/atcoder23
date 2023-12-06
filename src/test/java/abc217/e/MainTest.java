package abc217.e;

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
        "8" + System.lineSeparator() +
            "1 4" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "1 0" + System.lineSeparator() +
            "2";
    String output =
        "1" + System.lineSeparator() +
            "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "9" + System.lineSeparator() +
            "1 5" + System.lineSeparator() +
            "1 5" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "1 6" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2";
    String output =
        "5" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "5";

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

