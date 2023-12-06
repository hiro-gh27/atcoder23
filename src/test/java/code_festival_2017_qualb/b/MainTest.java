package code_festival_2017_qualb.b;

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
            "3 1 4 1 5" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "5 4 3";
    String output =
        "YES";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "7" + System.lineSeparator() +
            "100 200 500 700 1200 1600 2000" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "100 200 500 700 1600 1600";
    String output =
        "NO";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "1" + System.lineSeparator() +
            "800" + System.lineSeparator() +
            "5" + System.lineSeparator() +
            "100 100 100 100 100";
    String output =
        "NO";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "15" + System.lineSeparator() +
            "1 2 2 3 3 3 4 4 4 4 5 5 5 5 5" + System.lineSeparator() +
            "9" + System.lineSeparator() +
            "5 4 3 2 1 2 3 4 5";
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