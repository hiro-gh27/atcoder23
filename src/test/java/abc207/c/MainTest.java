package abc207.c;

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
            "1 1 2" + System.lineSeparator() +
            "2 2 3" + System.lineSeparator() +
            "3 2 4";
    String output =
        "2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "19" + System.lineSeparator() +
            "4 210068409 221208102" + System.lineSeparator() +
            "4 16698200 910945203" + System.lineSeparator() +
            "4 76268400 259148323" + System.lineSeparator() +
            "4 370943597 566244098" + System.lineSeparator() +
            "1 428897569 509621647" + System.lineSeparator() +
            "4 250946752 823720939" + System.lineSeparator() +
            "1 642505376 868415584" + System.lineSeparator() +
            "2 619091266 868230936" + System.lineSeparator() +
            "2 306543999 654038915" + System.lineSeparator() +
            "4 486033777 715789416" + System.lineSeparator() +
            "1 527225177 583184546" + System.lineSeparator() +
            "2 885292456 900938599" + System.lineSeparator() +
            "3 264004185 486613484" + System.lineSeparator() +
            "2 345310564 818091848" + System.lineSeparator() +
            "1 152544274 521564293" + System.lineSeparator() +
            "4 13819154 555218434" + System.lineSeparator() +
            "3 507364086 545932412" + System.lineSeparator() +
            "4 797872271 935850549" + System.lineSeparator() +
            "2 415488246 685203817";
    String output =
        "102";

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