package abc261.d;
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
        "6 3" + System.lineSeparator() +
            "2 7 1 8 2 8" + System.lineSeparator() +
            "2 10" + System.lineSeparator() +
            "3 1" + System.lineSeparator() +
            "5 5";
    String output =
        "48";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3 2" + System.lineSeparator() +
            "1000000000 1000000000 1000000000" + System.lineSeparator() +
            "1 1000000000" + System.lineSeparator() +
            "3 1000000000";
    String output =
        "5000000000";

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
