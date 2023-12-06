package abc291.d;
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
            "1 2" + System.lineSeparator() +
            "4 2" + System.lineSeparator() +
            "3 4";
    String output =
        "4";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4" + System.lineSeparator() +
            "1 5" + System.lineSeparator() +
            "2 6" + System.lineSeparator() +
            "3 7" + System.lineSeparator() +
            "4 8";
    String output =
        "16";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "8" + System.lineSeparator() +
            "877914575 602436426" + System.lineSeparator() +
            "861648772 623690081" + System.lineSeparator() +
            "476190629 262703497" + System.lineSeparator() +
            "971407775 628894325" + System.lineSeparator() +
            "822804784 450968417" + System.lineSeparator() +
            "161735902 822804784" + System.lineSeparator() +
            "161735902 822804784" + System.lineSeparator() +
            "822804784 161735902";
    String output =
        "48";

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
