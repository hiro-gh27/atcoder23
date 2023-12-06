package arc039.a;

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
        "567 234";
    String output =
        "733";

    assertIO(input, output);
  }

  @Test
  public void 入力例2() throws Exception {
    String input =
        "999 100";
    String output =
        "899";

    assertIO(input, output);
  }

  @Test
  public void 入力例3() throws Exception {
    String input =
        "100 999";
    String output =
        "-99";

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

