package abc286.d;


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
        "2 19" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "5 6";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2 18" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "5 6";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "3 1001" + System.lineSeparator() +
            "1 1" + System.lineSeparator() +
            "2 1" + System.lineSeparator() +
            "100 10";
    String output =
        "Yes";

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
