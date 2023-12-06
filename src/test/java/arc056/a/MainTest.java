package arc056.a;

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
        "3 7 10 3";
    String output =
        "24";

    assertIO(input, output);
  }

  @Test
  public void 入力例2() throws Exception {
    String input =
        "4 5 11 3";
    String output =
        "20";

    assertIO(input, output);
  }

  @Test
  public void 入力例3() throws Exception {
    String input =
        "3 8 3 3";
    String output =
        "8";

    assertIO(input, output);
  }

  @Test
  public void 入力例4() throws Exception {
    String input =
        "3 8 2 3";
    String output =
        "6";

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
