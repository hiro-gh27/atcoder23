package abc225.c;

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
            "5 6 7" + System.lineSeparator() +
            "12 13 14"+ System.lineSeparator() +
            "19 20 21";
    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "3 1" + System.lineSeparator() +
            "1" + System.lineSeparator() +
            "8" + System.lineSeparator() +
            "1";

    String output =
        "No";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "10 4" + System.lineSeparator() +
            "1346 1347 1348 1349" + System.lineSeparator() +
            "1353 1354 1355 1356" + System.lineSeparator() +
            "1360 1361 1362 1363" + System.lineSeparator() +
            "1367 1368 1369 1370" + System.lineSeparator() +
            "1374 1375 1376 1377" + System.lineSeparator() +
            "1381 1382 1383 1384" + System.lineSeparator() +
            "1388 1389 1390 1391" + System.lineSeparator() +
            "1395 1396 1397 1398" + System.lineSeparator() +
            "1402 1403 1404 1405" + System.lineSeparator() +
            "1409 1410 1411 1412";
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