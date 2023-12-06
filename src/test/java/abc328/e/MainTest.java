package abc328.e;

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
        "5 6 328" + System.lineSeparator() +
            "1 2 99" + System.lineSeparator() +
            "1 3 102" + System.lineSeparator() +
            "2 3 86" + System.lineSeparator() +
            "2 4 94" + System.lineSeparator() +
            "2 5 95" + System.lineSeparator() +
            "3 4 81";
    String output =
        "33";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "6 5 998244353" + System.lineSeparator() +
            "1 2 337361568" + System.lineSeparator() +
            "1 6 450343304" + System.lineSeparator() +
            "2 3 61477244" + System.lineSeparator() +
            "2 5 745383438" + System.lineSeparator() +
            "4 5 727360840";
    String output =
        "325437688";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "8 28 936294041850197" + System.lineSeparator() +
            "1 2 473294720906780" + System.lineSeparator() +
            "1 3 743030800139244" + System.lineSeparator() +
            "1 4 709363019414774" + System.lineSeparator() +
            "1 5 383643612490312" + System.lineSeparator() +
            "1 6 557102781022861" + System.lineSeparator() +
            "1 7 623179288538138" + System.lineSeparator() +
            "1 8 739618599410809" + System.lineSeparator() +
            "2 3 857687812294404" + System.lineSeparator() +
            "2 4 893923168139714" + System.lineSeparator() +
            "2 5 581822471860662" + System.lineSeparator() +
            "2 6 740549363586558" + System.lineSeparator() +
            "2 7 307226438833222" + System.lineSeparator() +
            "2 8 447399029952998" + System.lineSeparator() +
            "3 4 636318083622768" + System.lineSeparator() +
            "3 5 44548707643622" + System.lineSeparator() +
            "3 6 307262781240755" + System.lineSeparator() +
            "3 7 12070267388230" + System.lineSeparator() +
            "3 8 700247263184082" + System.lineSeparator() +
            "4 5 560567890325333" + System.lineSeparator() +
            "4 6 704726113717147" + System.lineSeparator() +
            "4 7 588263818615687" + System.lineSeparator() +
            "4 8 549007536393172" + System.lineSeparator() +
            "5 6 779230871080408" + System.lineSeparator() +
            "5 7 825982583786498" + System.lineSeparator() +
            "5 8 713928998174272" + System.lineSeparator() +
            "6 7 751331074538826" + System.lineSeparator() +
            "6 8 449873635430228" + System.lineSeparator() +
            "7 8 11298381761479";
    String output =
        "11360716373";

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
