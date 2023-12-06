package abc041.c;

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
        "3" + System.lineSeparator() +
            "140 180 160";
    String output =
        "2" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例2() throws Exception {
    String input =
        "2" + System.lineSeparator() +
            "1000000000 1";
    String output =
        "1" + System.lineSeparator() +
            "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例3() throws Exception {
    String input =
        "8" + System.lineSeparator() +
            "3 1 4 15 9 2 6 5";
    String output =
        "4" + System.lineSeparator() +
            "5" + System.lineSeparator() +
            "7" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "2";

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
