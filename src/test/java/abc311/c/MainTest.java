package abc311.c;


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
        "7" + System.lineSeparator() +
            "6 7 2 1 3 4 5";
    String output =
        "4" + System.lineSeparator() +
            "7 5 3 2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2" + System.lineSeparator() +
            "2 1";
    String output =
        "2" + System.lineSeparator() +
            "1 2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "8" + System.lineSeparator() +
            "3 7 4 7 3 3 8 2";
    String output =
        "3" + System.lineSeparator() +
            "2 7 8";

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
