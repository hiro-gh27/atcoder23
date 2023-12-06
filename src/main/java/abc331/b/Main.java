package abc331.b;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    int N = sc.nextInt();
    long S = sc.nextInt();
    long M = sc.nextInt();
    long L = sc.nextInt();
    HashMap<Integer, Long> priceMap = new HashMap<>();
    priceMap.put(6, S);
    priceMap.put(8, M);
    priceMap.put(12, L);

    long[] dp = new long[N+100];
    for (Entry<Integer, Long> p : priceMap.entrySet()) {
      dp[p.getKey()] = p.getValue();
    }

    for (int i = 0; i < dp.length; i++) {
      if (dp[i] == 0) continue;
      for (Entry<Integer, Long> price : priceMap.entrySet()) {
        if (i+price.getKey() < dp.length){
          if (dp[i+price.getKey()] == 0) {
            dp[i+ price.getKey()] = dp[i]+price.getValue();
          } else {
            dp[i+price.getKey()] = Math.min(dp[i+price.getKey()], dp[i]+price.getValue());
          }
        }
      }
    }

    long ans = Long.MAX_VALUE;
    for (int i = N; i < dp.length; i++) {
      if (dp[i] == 0) continue;
      ans = Math.min(ans, dp[i]);
    }
    out.println(ans);
  }

  public ArrayList<Character> generateLowercaseAlphabeticList() {
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'a'; i <= 'z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  public ArrayList<Character> generateUppercaseAlphabeticList() {
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'A'; i <= 'Z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  // logger 
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
