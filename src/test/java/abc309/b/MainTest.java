package abc309.b;

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
            "0101" + System.lineSeparator() +
            "1101" + System.lineSeparator() +
            "1111" + System.lineSeparator() +
            "0000";
    String output =
        "1010" + System.lineSeparator() +
            "1101" + System.lineSeparator() +
            "0111" + System.lineSeparator() +
            "0001";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "2" + System.lineSeparator() +
            "11" + System.lineSeparator() +
            "11";
    String output =
        "11" + System.lineSeparator() +
            "11";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "5" + System.lineSeparator() +
            "01010" + System.lineSeparator() +
            "01001" + System.lineSeparator() +
            "10110" + System.lineSeparator() +
            "00110" + System.lineSeparator() +
            "01010";
    String output =
        "00101" + System.lineSeparator() +
            "11000" + System.lineSeparator() +
            "00111" + System.lineSeparator() +
            "00110" + System.lineSeparator() +
            "10100";

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
