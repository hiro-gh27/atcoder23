package igitalarts.a;

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
        "abc aaa ababa abcba abc" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "abc" + System.lineSeparator() +
            "**a**";
    String output =
        "*** aaa ***** abcba ***";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "aaaa aaa aaaaaa aaaa" + System.lineSeparator() +
            "3" + System.lineSeparator() +
            "a" + System.lineSeparator() +
            "aa" + System.lineSeparator() +
            "aaa";
    String output =
        "aaaa *** aaaaaa aaaa";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "i have a pen" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "*";
    String output =
        "* have * pen";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "digital arts" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "digital*arts";
    String output =
        "digital arts";

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
