package arc073.a;

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
        "2 4" + System.lineSeparator() +
            "0 3";
    String output =
        "7";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2 4" + System.lineSeparator() +
            "0 5";
    String output =
        "8";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "4 1000000000" + System.lineSeparator() +
            "0 1000 1000000 1000000000";
    String output =
        "2000000000";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "1 1" + System.lineSeparator() +
            "0";
    String output =
        "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_5() throws Exception {
    String input =
        "9 10" + System.lineSeparator() +
            "0 3 5 7 100 110 200 300 311";
    String output =
        "67";

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

