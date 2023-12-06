package abc312.b;

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
        "19 18" + System.lineSeparator() +
            "###......###......" + System.lineSeparator() +
            "###......###......" + System.lineSeparator() +
            "###..#...###..#..." + System.lineSeparator() +
            ".............#..." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            "......###......###" + System.lineSeparator() +
            "......###......###" + System.lineSeparator() +
            "......###......###" + System.lineSeparator() +
            ".###.............." + System.lineSeparator() +
            ".###......##......" + System.lineSeparator() +
            ".###.............." + System.lineSeparator() +
            "............###..." + System.lineSeparator() +
            "...##.......###..." + System.lineSeparator() +
            "...##.......###..." + System.lineSeparator() +
            ".......###........" + System.lineSeparator() +
            ".......###........" + System.lineSeparator() +
            ".......###........" + System.lineSeparator() +
            "........#.........";
    String output =
        "1 1" + System.lineSeparator() +
            "1 10" + System.lineSeparator() +
            "7 7" + System.lineSeparator() +
            "10 2";

    assertIO(input, output);
  }

  @Test
  public void 入力例_2() throws Exception {
    String input =
        "9 21" + System.lineSeparator() +
            "###.#...........#.###" + System.lineSeparator() +
            "###.#...........#.###" + System.lineSeparator() +
            "###.#...........#.###" + System.lineSeparator() +
            "....#...........#...." + System.lineSeparator() +
            "#########...#########" + System.lineSeparator() +
            "....#...........#...." + System.lineSeparator() +
            "....#.###...###.#...." + System.lineSeparator() +
            "....#.###...###.#...." + System.lineSeparator() +
            "....#.###...###.#....";
    String output =
        "1 1";

    assertIO(input, output);
  }

  @Test
  public void 入力例_3() throws Exception {
    String input =
        "18 18" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            "######............" + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            ".................." + System.lineSeparator() +
            "............######" + System.lineSeparator() +
            "............######" + System.lineSeparator() +
            "............######" + System.lineSeparator() +
            "............######" + System.lineSeparator() +
            "............######" + System.lineSeparator() +
            "............######";
    String output =
        "";

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
