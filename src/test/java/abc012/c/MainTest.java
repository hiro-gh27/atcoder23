package abc012.c;

import static org.hamcrest.CoreMatchers.is;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void 入力例1() throws Exception {
    String input =
        "2013";
    String output =
        "2 x 6" + System.lineSeparator() +
            "3 x 4" + System.lineSeparator() +
            "4 x 3" + System.lineSeparator() +
            "6 x 2";

    assertIO(input, output);
  }

  @Test
  public void 入力例2() throws Exception {
    String input =
        "2024";
    String output =
        "1 x 1";

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
