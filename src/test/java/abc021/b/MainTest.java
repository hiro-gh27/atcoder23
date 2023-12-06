package abc021.b;

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
        "5" + System.lineSeparator() +
            "1 5" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "3 4 2";
    String output =
        "YES";

    assertIO(input, output);
  }

  @Test
  public void 入力例2() throws Exception {
    String input =
        "7" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "4" + System.lineSeparator() +
            "2 4 2 7";
    String output =
        "NO";

    assertIO(input, output);
  }

  @Test
  public void 入力例3() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "1 4" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2 1 3";
    String output =
        "NO";

    assertIO(input, output);
  }

  @Test
  public void 入力例4() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "1 4" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "2 4 3";
    String output =
        "NO";

    assertIO(input, output);
  }

  @Test
  public void 入力例5() throws Exception {
    String input =
        "20" + System.lineSeparator() +
            "1 4" + System.lineSeparator() +
            "12" + System.lineSeparator() +
            "2 3 5 7 8 9 10 11 12 15 13 14";
    String output =
        "YES";

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
