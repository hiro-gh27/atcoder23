package abc261.d;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    int M = sc.nextInt();

    int[] points = new int[N];
    HashMap<Integer, Integer> bonusMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      points[i] = sc.nextInt();
    }
    for (int i = 0; i < M; i++) {
      bonusMap.put(sc.nextInt(), sc.nextInt());
    }

    long[][] dp = new long[N+1][N+1];
    for (long[] longs : dp) {
      Arrays.fill(longs, -1);
    }
    dp[0][0] = 0;

    for (int time = 0; time < dp.length-1; time++) {
      for (int continuous = 0; continuous < dp[time].length-1; continuous++) {
        if (dp[time][continuous] == -1) continue;
        // 裏
        dp[time+1][0] = Math.max(dp[time+1][0], dp[time][continuous]);

        // 表
        long nextPoint = dp[time][continuous] + points[time] + bonusMap.getOrDefault(continuous+1, 0);
        dp[time+1][continuous+1] = Math.max(dp[time][continuous], nextPoint);
      }
    }

    var ans = Arrays.stream(dp[dp.length-1]).max();
    out.println(ans.orElseGet(()->0));
    



    
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
