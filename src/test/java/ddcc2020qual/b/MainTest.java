package ddcc2020qual.b;


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
        "3" + System.lineSeparator() +
            "2 4 3";
    String output =
        "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "12" + System.lineSeparator() +
            "100 104 102 105 103 103 101 105 104 102 104 101";
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
