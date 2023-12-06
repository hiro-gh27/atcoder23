package abc311.b;

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
        "3 5" + System.lineSeparator() +
            "xooox" + System.lineSeparator() +
            "oooxx" + System.lineSeparator() +
            "oooxo";
    String output =
        "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3 3" + System.lineSeparator() +
            "oxo" + System.lineSeparator() +
            "oxo" + System.lineSeparator() +
            "oxo";
    String output =
        "1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "3 3" + System.lineSeparator() +
            "oox" + System.lineSeparator() +
            "oxo" + System.lineSeparator() +
            "xoo";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "1 7" + System.lineSeparator() +
            "ooooooo";
    String output =
        "7";

    assertIO(input, output);
  }

  @Test
  public void 入力例_5() throws Exception {
    String input =
        "5 15" + System.lineSeparator() +
            "oxooooooooooooo" + System.lineSeparator() +
            "oxooxooooooooox" + System.lineSeparator() +
            "oxoooooooooooox" + System.lineSeparator() +
            "oxxxooooooxooox" + System.lineSeparator() +
            "oxooooooooxooox";
    String output =
        "5";

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
