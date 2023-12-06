package abc011.c;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
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
    int N = sc.nextInt();

    int[] minus = new int[3];
    for (int i = 0; i < 3; i++) {
      minus[i] = i + 1;
    }
    HashSet<Integer> ngSet = new HashSet<>();
    ngSet.add(sc.nextInt());
    ngSet.add(sc.nextInt());
    ngSet.add(sc.nextInt());

    int[] arrived = new int[N+1];
    Arrays.fill(arrived, 1000);
    arrived[N] = 0;
    if (ngSet.contains(N)){
      arrived[N] = 1000;
    }

    for (int i = arrived.length - 1; i >= 0; i--) {
      if (arrived[i] != 1000){
        for (int j = 0; j < minus.length; j++) {
          int reachableValue = i - minus[j];
          if (reachableValue < 0) continue;
          if (!ngSet.contains(reachableValue)){
            arrived[reachableValue] = Math.min(arrived[reachableValue], arrived[i]+1);
          }else {
            LOGGER.info("");
          }
        }
      }
    }
    
    if (arrived[0] <= 100) out.println("YES");
    else out.println("NO");

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
