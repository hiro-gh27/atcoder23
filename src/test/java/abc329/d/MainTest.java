package abc329.d;

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
        "3 7" + System.lineSeparator() +
            "1 2 2 3 1 3 3";
    String output =
        "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "100 5" + System.lineSeparator() +
            "100 90 80 70 60";
    String output =
        "100" + System.lineSeparator() +
            "90" + System.lineSeparator() +
            "80" + System.lineSeparator() +
            "70" + System.lineSeparator() +
            "60";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "9 8" + System.lineSeparator() +
            "8 8 2 2 8 8 2 2";
    String output =
        "8" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "2";

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

