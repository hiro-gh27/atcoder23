package abc252.c;

import java.io.*;
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
    int[][] slot = new int[N][10];

    for (int i = 0; i < N; i++) {
      char[] reels = sc.next().toCharArray();
      for (int j = 0; j < reels.length; j++) {
        int value = reels[j] - '0';
        slot[i][value] = j;
      }
    }

    int min = Integer.MAX_VALUE;
    for (int reelTarget = 0; reelTarget < 10; reelTarget++) {
      HashSet<Integer> timing = new HashSet<>();

      for (int i = 0; i < slot.length; i++) {
        int reelIndex = slot[i][reelTarget];
        while (timing.contains(reelIndex)){
          reelIndex += 10;
        }
        timing.add(reelIndex);
      }

      int totalCost = timing.stream().mapToInt(x->x).max().orElseThrow();
      min = Math.min(min, totalCost);
    }

    out.println(min);

    
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
