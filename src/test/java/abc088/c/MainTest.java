package abc088.c;


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
        "1 0 1" + System.lineSeparator() +
            "2 1 2" + System.lineSeparator() +
            "1 0 1";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2 2 2" + System.lineSeparator() +
            "2 1 2" + System.lineSeparator() +
            "2 2 2";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "0 8 8" + System.lineSeparator() +
            "0 8 8" + System.lineSeparator() +
            "0 8 8";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "1 8 6" + System.lineSeparator() +
            "2 9 7" + System.lineSeparator() +
            "0 7 7";
    String output =
        "No";

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
