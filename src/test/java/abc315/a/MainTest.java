package abc315.a;

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
        "atcoder";
    String output =
        "tcdr";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "xyz";
    String output =
        "xyz";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "aaaabbbbcccc";
    String output =
        "bbbbcccc";

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
