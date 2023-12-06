package abc324.c;


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
        "5 ababc" + System.lineSeparator() +
            "ababc" + System.lineSeparator() +
            "babc" + System.lineSeparator() +
            "abacbc" + System.lineSeparator() +
            "abdbc" + System.lineSeparator() +
            "abbac";
    String output =
        "4" + System.lineSeparator() +
            "1 2 3 4";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "1 aoki" + System.lineSeparator() +
            "takahashi";
    String output =
        "0";

    assertIO(input, output);
  }

  @Test
  public void 入力例_4() throws Exception {
    String input =
        "1 abcd" + System.lineSeparator() +
            "abcde";
    String output =
        "1" + System.lineSeparator() +
        "1";
    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "9 atcoder" + System.lineSeparator() +
            "atoder" + System.lineSeparator() +
            "atcode" + System.lineSeparator() +
            "athqcoder" + System.lineSeparator() +
            "atcoder" + System.lineSeparator() +
            "tacoder" + System.lineSeparator() +
            "jttcoder" + System.lineSeparator() +
            "atoder" + System.lineSeparator() +
            "atceoder" + System.lineSeparator() +
            "atcoer";
    String output =
        "6" + System.lineSeparator() +
            "1 2 4 7 8 9";

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

