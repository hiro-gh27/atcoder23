package abc258.b;


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
        "4" + System.lineSeparator() +
            "1160" + System.lineSeparator() +
            "1110" + System.lineSeparator() +
            "7119" + System.lineSeparator() +
            "1891";
    String output =
        "9911";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "10" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111" + System.lineSeparator() +
            "1111111111";
    String output =
        "1111111111";

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

