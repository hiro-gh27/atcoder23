package abc131.d;

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
        "5" + System.lineSeparator() +
            "2 4" + System.lineSeparator() +
            "1 9" + System.lineSeparator() +
            "1 8" + System.lineSeparator() +
            "4 9" + System.lineSeparator() +
            "3 12";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3" + System.lineSeparator() +
            "334 1000" + System.lineSeparator() +
            "334 1000" + System.lineSeparator() +
            "334 1000";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "30" + System.lineSeparator() +
            "384 8895" + System.lineSeparator() +
            "1725 9791" + System.lineSeparator() +
            "170 1024" + System.lineSeparator() +
            "4 11105" + System.lineSeparator() +
            "2 6" + System.lineSeparator() +
            "578 1815" + System.lineSeparator() +
            "702 3352" + System.lineSeparator() +
            "143 5141" + System.lineSeparator() +
            "1420 6980" + System.lineSeparator() +
            "24 1602" + System.lineSeparator() +
            "849 999" + System.lineSeparator() +
            "76 7586" + System.lineSeparator() +
            "85 5570" + System.lineSeparator() +
            "444 4991" + System.lineSeparator() +
            "719 11090" + System.lineSeparator() +
            "470 10708" + System.lineSeparator() +
            "1137 4547" + System.lineSeparator() +
            "455 9003" + System.lineSeparator() +
            "110 9901" + System.lineSeparator() +
            "15 8578" + System.lineSeparator() +
            "368 3692" + System.lineSeparator() +
            "104 1286" + System.lineSeparator() +
            "3 4" + System.lineSeparator() +
            "366 12143" + System.lineSeparator() +
            "7 6649" + System.lineSeparator() +
            "610 2374" + System.lineSeparator() +
            "152 7324" + System.lineSeparator() +
            "4 7042" + System.lineSeparator() +
            "292 11386" + System.lineSeparator() +
            "334 5720";
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

