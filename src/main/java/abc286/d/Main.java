package abc286.d;

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
    int numberOfTypes = sc.nextInt();
    int target = sc.nextInt();

    ArrayList<Integer> coins = new ArrayList<>();
    for (int i = 0; i < numberOfTypes; i++) {
      int yen = sc.nextInt();
      int numberOfCoins = sc.nextInt();
      for (int j = 0; j < numberOfCoins; j++) {
        coins.add(yen);
      }
    }

    boolean[][] dp = new boolean[coins.size()+1][target+1];
    dp[0][0] = true;

    for (int i = 0; i < coins.size(); i++) {
      int theCoin = coins.get(i);
      for (int j = 0; j < dp[i].length; j++) {
        if (dp[i][j]){
          dp[i+1][j] = true;
          int next = j + theCoin;
          if (next < dp[i].length){
            dp[i+1][next] = true;
          }
        }
      }
    }

    for (boolean[] booleans : dp) {
      if (booleans[target]) {
        out.println("Yes");
        return;
      }
    }

    out.println("No");

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
