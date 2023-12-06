package abc209.c;

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
        "4 1" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "2 4" + System.lineSeparator() +
            "1 2";
    String output =
        "Road";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "5 2" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "3 4" + System.lineSeparator() +
            "4 5" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "1 5";
    String output =
        "Town" + System.lineSeparator() +
            "Town";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "9 9" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "5 6" + System.lineSeparator() +
            "4 8" + System.lineSeparator() +
            "8 9" + System.lineSeparator() +
            "4 5" + System.lineSeparator() +
            "3 4" + System.lineSeparator() +
            "1 9" + System.lineSeparator() +
            "3 7" + System.lineSeparator() +
            "7 9" + System.lineSeparator() +
            "2 5" + System.lineSeparator() +
            "2 6" + System.lineSeparator() +
            "4 6" + System.lineSeparator() +
            "2 4" + System.lineSeparator() +
            "5 8" + System.lineSeparator() +
            "7 8" + System.lineSeparator() +
            "3 6" + System.lineSeparator() +
            "5 6";
    String output =
        "Town" + System.lineSeparator() +
            "Road" + System.lineSeparator() +
            "Town" + System.lineSeparator() +
            "Town" + System.lineSeparator() +
            "Town" + System.lineSeparator() +
            "Town" + System.lineSeparator() +
            "Road" + System.lineSeparator() +
            "Road" + System.lineSeparator() +
            "Road";

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
