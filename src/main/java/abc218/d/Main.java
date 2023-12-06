package abc218.d;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    ArrayList<Point> points = new ArrayList<>();

    HashSet<Point> pointHashSet = new HashSet<>();
    for (int i = 0; i < N; i++) {
      Point point = new Point(sc.nextInt(), sc.nextInt());
      points.add(point);
    }

    HashMap<Integer, Integer> xy = new HashMap<>();
    for (int i = 0; i < points.size(); i++) {
      Point point = points.get(i);
      pointHashSet.add(point);
    }

    ArrayList<Pair<Point>> pairs = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      for (int j = i+1; j < points.size(); j++) {
        pairs.add(new Pair<>(points.get(i), points.get(j)));
      }
    }

    int count = 0;
    for (Pair<Point> pair : pairs) {
      Point first = pair.getFirst();
      Point second = pair.getSecond();

      // 対角線チェック
      if (first.x == second.x) continue;
      if (first.y == second.y) continue;

      Point right = new Point(first.x, second.y);
      Point left = new Point(second.x, first.y);

      if (!pointHashSet.contains(right)) continue;
      if (!pointHashSet.contains(left)) continue;


      count++;

    }

    out.println(count>0? count/2 : 0);

  }

  static class Pair<T>{
    private T first;
    private T second;

    Pair(T first, T second) {
      this.first = first;
      this.second = second;
    }

    public T getFirst() {
      return first;
    }

    public void setFirst(T first) {
      this.first = first;
    }

    public T getSecond() {
      return second;
    }

    public void setSecond(T second) {
      this.second = second;
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
