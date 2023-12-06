package abc293.c;

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
        "3 3" + System.lineSeparator() +
            "3 2 2" + System.lineSeparator() +
            "2 1 3" + System.lineSeparator() +
            "1 5 4";
    String output =
        "3";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "10 10" + System.lineSeparator() +
            "1 2 3 4 5 6 7 8 9 10" + System.lineSeparator() +
            "11 12 13 14 15 16 17 18 19 20" + System.lineSeparator() +
            "21 22 23 24 25 26 27 28 29 30" + System.lineSeparator() +
            "31 32 33 34 35 36 37 38 39 40" + System.lineSeparator() +
            "41 42 43 44 45 46 47 48 49 50" + System.lineSeparator() +
            "51 52 53 54 55 56 57 58 59 60" + System.lineSeparator() +
            "61 62 63 64 65 66 67 68 69 70" + System.lineSeparator() +
            "71 72 73 74 75 76 77 78 79 80" + System.lineSeparator() +
            "81 82 83 84 85 86 87 88 89 90" + System.lineSeparator() +
            "91 92 93 94 95 96 97 98 99 100";
    String output =
        "48620";

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

