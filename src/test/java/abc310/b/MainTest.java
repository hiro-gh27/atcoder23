package abc310.b;

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
        "5 6" + System.lineSeparator() +
            "10000 2 1 3" + System.lineSeparator() +
            "15000 3 1 2 4" + System.lineSeparator() +
            "30000 3 1 3 5" + System.lineSeparator() +
            "35000 2 1 5" + System.lineSeparator() +
            "100000 6 1 2 3 4 5 6";
    String output =
        "Yes";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "4 4" + System.lineSeparator() +
            "3 1 1" + System.lineSeparator() +
            "3 1 2" + System.lineSeparator() +
            "3 1 2" + System.lineSeparator() +
            "4 2 2 3";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "20 10" + System.lineSeparator() +
            "72036 3 3 4 9" + System.lineSeparator() +
            "7716 4 1 2 3 6" + System.lineSeparator() +
            "54093 5 1 6 7 8 10" + System.lineSeparator() +
            "25517 7 3 4 5 6 7 9 10" + System.lineSeparator() +
            "96930 8 2 3 4 6 7 8 9 10" + System.lineSeparator() +
            "47774 6 2 4 5 6 7 9" + System.lineSeparator() +
            "36959 5 1 3 4 5 8" + System.lineSeparator() +
            "46622 7 1 2 3 5 6 8 10" + System.lineSeparator() +
            "34315 9 1 3 4 5 6 7 8 9 10" + System.lineSeparator() +
            "54129 7 1 3 4 6 7 8 9" + System.lineSeparator() +
            "4274 5 2 4 7 9 10" + System.lineSeparator() +
            "16578 5 2 3 6 7 9" + System.lineSeparator() +
            "61809 4 1 2 4 5" + System.lineSeparator() +
            "1659 5 3 5 6 9 10" + System.lineSeparator() +
            "59183 5 1 2 3 4 9" + System.lineSeparator() +
            "22186 4 3 5 6 8" + System.lineSeparator() +
            "98282 4 1 4 7 10" + System.lineSeparator() +
            "72865 8 1 2 3 4 6 8 9 10" + System.lineSeparator() +
            "33796 6 1 3 5 7 9 10" + System.lineSeparator() +
            "74670 4 1 2 6 8";
    String output =
        "Yes";

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
