package abc330.e;

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
        "8 5" + System.lineSeparator() +
            "2 0 2 2 1 1 2 5" + System.lineSeparator() +
            "4 3" + System.lineSeparator() +
            "4 4" + System.lineSeparator() +
            "6 3" + System.lineSeparator() +
            "8 1000000000" + System.lineSeparator() +
            "2 1";
    String output =
        "4" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "5" + System.lineSeparator() +
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
