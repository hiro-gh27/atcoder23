package abc314.b;
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
            "3" + System.lineSeparator() +
            "7 19 20" + System.lineSeparator() +
            "4" + System.lineSeparator() +
            "4 19 24 0" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "26 10" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "19 31 24" + System.lineSeparator() +
            "19";
    String output =
        "2" + System.lineSeparator() +
            "1 4";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "0";
    String output =
        "0";

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

