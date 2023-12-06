package abc330.d;

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
        "3" + System.lineSeparator() +
            "ooo" + System.lineSeparator() +
            "oxx" + System.lineSeparator() +
            "xxo";
    String output =
        "4";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "oxxx" + System.lineSeparator() +
            "xoxx" + System.lineSeparator() +
            "xxox" + System.lineSeparator() +
            "xxxo";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "15" + System.lineSeparator() +
            "xooxxooooxxxoox" + System.lineSeparator() +
            "oxxoxoxxxoxoxxo" + System.lineSeparator() +
            "oxxoxoxxxoxoxxx" + System.lineSeparator() +
            "ooooxooooxxoxxx" + System.lineSeparator() +
            "oxxoxoxxxoxoxxx" + System.lineSeparator() +
            "oxxoxoxxxoxoxxo" + System.lineSeparator() +
            "oxxoxooooxxxoox" + System.lineSeparator() +
            "xxxxxxxxxxxxxxx" + System.lineSeparator() +
            "xooxxxooxxxooox" + System.lineSeparator() +
            "oxxoxoxxoxoxxxo" + System.lineSeparator() +
            "xxxoxxxxoxoxxoo" + System.lineSeparator() +
            "xooxxxooxxoxoxo" + System.lineSeparator() +
            "xxxoxxxxoxooxxo" + System.lineSeparator() +
            "oxxoxoxxoxoxxxo" + System.lineSeparator() +
            "xooxxxooxxxooox";
    String output =
        "2960";

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
