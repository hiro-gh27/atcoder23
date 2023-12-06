package abc320.c;


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
        "10" + System.lineSeparator() +
            "1937458062" + System.lineSeparator() +
            "8124690357" + System.lineSeparator() +
            "2385760149";
    String output =
        "6";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "20" + System.lineSeparator() +
            "01234567890123456789" + System.lineSeparator() +
            "01234567890123456789" + System.lineSeparator() +
            "01234567890123456789";
    String output =
        "20";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "5" + System.lineSeparator() +
            "11111" + System.lineSeparator() +
            "22222" + System.lineSeparator() +
            "33333";
    String output =
        "-1";

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
