package abc149.d;
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
        "5 2" + System.lineSeparator() +
            "8 7 6" + System.lineSeparator() +
            "rsrpr";
    String output =
        "27";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "7 1" + System.lineSeparator() +
            "100 10 1" + System.lineSeparator() +
            "ssssppr";
    String output =
        "211";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "30 5" + System.lineSeparator() +
            "325 234 123" + System.lineSeparator() +
            "rspsspspsrpspsppprpsprpssprpsr";
    String output =
        "4996";

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
