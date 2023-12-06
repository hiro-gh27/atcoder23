package abc331.e;
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
        "2 3 3" + System.lineSeparator() +
            "2 1" + System.lineSeparator() +
            "10 30 20" + System.lineSeparator() +
            "1 2" + System.lineSeparator() +
            "2 1" + System.lineSeparator() +
            "2 3";
    String output =
        "31";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2 1 0" + System.lineSeparator() +
            "1000000000 1" + System.lineSeparator() +
            "1000000000";
    String output =
        "2000000000";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "10 10 10" + System.lineSeparator() +
            "47718 21994 74148 76721 98917 73766 29598 59035 69293 29127" + System.lineSeparator() +
            "7017 46004 16086 62644 74928 57404 32168 45794 19493 71590" + System.lineSeparator() +
            "1 3" + System.lineSeparator() +
            "2 6" + System.lineSeparator() +
            "4 5" + System.lineSeparator() +
            "5 4" + System.lineSeparator() +
            "5 5" + System.lineSeparator() +
            "5 6" + System.lineSeparator() +
            "5 7" + System.lineSeparator() +
            "5 8" + System.lineSeparator() +
            "5 10" + System.lineSeparator() +
            "7 3";
    String output =
        "149076";

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
