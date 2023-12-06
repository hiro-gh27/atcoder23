package arc156.a;

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
            "3" + System.lineSeparator() +
            "101" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "101101" + System.lineSeparator() +
            "5" + System.lineSeparator() +
            "11111" + System.lineSeparator() +
            "6" + System.lineSeparator() +
            "000000" + System.lineSeparator() +
            "30" + System.lineSeparator() +
            "111011100110101100101000000111";
    String output =
        "1" + System.lineSeparator() +
            "2" + System.lineSeparator() +
            "-1" + System.lineSeparator() +
            "0" + System.lineSeparator() +
            "8";

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
