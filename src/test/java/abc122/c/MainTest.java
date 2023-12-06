package abc122.c;
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
        "8 3" + System.lineSeparator() +
            "ACACTACG" + System.lineSeparator() +
            "3 7" + System.lineSeparator() +
            "2 3" + System.lineSeparator() +
            "1 8";
    String output =
        "2" + System.lineSeparator() +
            "0" + System.lineSeparator() +
            "3";

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


