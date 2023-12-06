package agc019.a;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
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
    int Q = sc.nextInt();
    int H = sc.nextInt();
    int S = sc.nextInt();
    int D = sc.nextInt();

    double target = sc.nextDouble();

    // 価格の見直し
    H = Math.min(Q*2, H);
    S = Math.min(H*2, S);
    D = Math.min(S*2, D);

    // 大きい順に処理する
    HashMap<Double, Integer> charges = new HashMap<>();
    charges.put(2.0, D);
    charges.put(1.0, S);
    charges.put(0.5, H);
    charges.put(0.25, Q);

    long cost = 0;
    for (Entry<Double, Integer> price : charges.entrySet()) {
      if (target >= price.getKey()){
        long count = (long) (target / price.getKey());
        target = target % price.getKey();
        cost += count * (long)price.getValue();
      }
    }

    out.println(cost);


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
