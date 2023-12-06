package arc039.a;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.*;

public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private static PrintWriter out;

  public static void main(String[] args) {
    LOGGER.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SingleLineFormatter());
    LOGGER.addHandler(handler);

    Main main = new Main();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    try {
      main.run(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    out.close();
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    char[] charNumbers = sc.nextLine().replace(" ", "").toCharArray();

    int max = calLettersDiff(charNumbers);
    for (int i = 0; i < charNumbers.length; i++) {
      for (int j = 0; j < 10; j++) {
        char[] copied = Arrays.copyOf(charNumbers, charNumbers.length);
        copied[i] = (char) (j + '0');
        max = Math.max(max, calLettersDiff(copied));
      }
    }
    out.println(max);

  }

  private int calLettersDiff(char[] letters){
    char[] first = Arrays.copyOfRange(letters, 0, 3);
    char[] second = Arrays.copyOfRange(letters, 3,6 );

    if (first[0] == '0' || second[0] == '0') return Integer.MIN_VALUE;

    return Integer.parseInt(new String(first)) - Integer.parseInt(new String(second));
  }

  static class SingleLineFormatter extends Formatter {

    private static final String format = "[%1$tF %1$tT] %2$s %n";

    @Override
    public String format(LogRecord record) {
      return String.format(format,
          new java.util.Date(record.getMillis()),
          record.getMessage()
      );
    }
  }

  /*
   * Form: http://codeforces.com/blog/entry/7018
   */
  private class MyScanner {

    BufferedReader br;
    StringTokenizer st;

    MyScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
