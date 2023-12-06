package abc173.c;

import java.io.*;
import java.util.ArrayList;
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
    int height = sc.nextInt();
    int width = sc.nextInt();
    int K = sc.nextInt();

    char[][] grid = new char[height][width];
    for (int i = 0; i < grid.length; i++) {
      grid[i] = sc.nextLine().toCharArray();
    }

    var heightCombination = getAllCombination(height);
    var widthCombination = getAllCombination(width);

    int ans = 0;
    for (ArrayList<Boolean> heightBooleans : heightCombination) {
      for (ArrayList<Boolean> wightBooleans : widthCombination) {
        if (executeTask(grid, heightBooleans, wightBooleans) == K){
          ans++;
        }
      }
    }
    out.println(ans);
  }

  private int executeTask(char[][] src, ArrayList<Boolean> heightCombination, ArrayList<Boolean> wightCombination){
    char[][] graph = Arrays.stream(src).map(char[]::clone).toArray(char[][]:: new);

    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        if (heightCombination.get(i) || wightCombination.get(j)){
          graph[i][j] = 'r';
        }
      }
    }

    int blackCount = 0;
    for (char[] outer : graph) {
      for (char inner : outer) {
        if (inner == '#')
          blackCount++;
      }
    }

    return blackCount;
  }



  private ArrayList<ArrayList<Boolean>> getAllCombination(int number){
    ArrayList<ArrayList<Boolean>> combinations = new ArrayList<>();
    for (int i = 0; i < Math.pow(2, number); i++) {
      ArrayList<Boolean> result = new ArrayList<>();
      for (int j = 0; j < number; j++) {
        if ( ((i >> j) & 1) == 1 ){
          result.add(true);
        }else {
          result.add(false);
        }
      }
      combinations.add(result);
    }
    return combinations;
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
