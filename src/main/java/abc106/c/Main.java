package abc106.c;

import java.io.*;
import java.util.ArrayList;
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
    char[] letters = sc.next().toCharArray();
    long k = sc.nextLong();

    ArrayList<Range> ranges = new ArrayList<>();
    for (int i = 1; i <= letters.length; i++) {
      int value = letters[i-1] - '0';
      boolean breakable = false;
      Range range;
      if (value == 1){
        range = new Range(i, i, true, true, value);
      }else {
        range = new Range(i, Long.MAX_VALUE, true, true, value);
        breakable = true;
      }
      ranges.add(range);
      if (breakable) break;
    }

    for (Range range : ranges) {
      if (range.isContain(k)){
        out.println(range.value);
        return;
      }
    }
    

  }

  private class Range{
    long from;

    long to;

    boolean fromInclusive;

    boolean toInclusive;

    int value;

    public Range(long from, long to, boolean fromInclusive, boolean toInclusive, int value) {
      this.from = from;
      this.to = to;
      this.fromInclusive = fromInclusive;
      this.toInclusive = toInclusive;
      this.value = value;
    }

    private boolean isContain(long x){
      if (x > from && x < to){
        return true;
      }
      if (to == Long.MAX_VALUE){
        return true;
      }

      if (fromInclusive && x == from){
        return true;
      }

      if (toInclusive && x == to){
        return true;

      }
      return false;
    }

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
