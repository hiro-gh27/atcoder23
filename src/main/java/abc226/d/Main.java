package abc226.d;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
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
    for (int i = 0; i < N; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      points.add(new Point(x, y));
    }

    HashSet<Vector> vectors = new HashSet<>();
    for (int i = 0; i < points.size(); i++) {
      for (int j = i+1; j < points.size(); j++) {
        Point pi = points.get(i);
        Point pj = points.get(j);

        int vx = pj.x - pi.x;
        int vy = pj.y - pi.y;

        if (vx == 0) {
          vectors.add(new Vector(0, 1));
          vectors.add(new Vector(0, -1));
          continue;
        } else if (vy == 0) {
          vectors.add(new Vector(1, 0));
          vectors.add(new Vector(-1, 0));
          continue;
        }

        int lcd = getMaxShareDivider(vx, vy);

        vectors.add(new Vector(vx/lcd, vy/lcd));
        vectors.add(new Vector(-(vx/lcd), -(vy/lcd)));
      }
    }



    out.println(vectors.size());
  }

  public static int getMaxShareDivider(int a, int b){
    a = Math.abs(a);
    b = Math.abs(b);
    if (b > a){
      int tmp = a;
      a = b;
      b = tmp;
    }

    if (b == 0){
      return a;
    }

    return getMaxShareDivider(b,a%b);

  }

  static class Vector{
    int x;
    int y;

    @Override
    public String toString() {
      return "Vector{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }

    public Vector(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Vector vector = (Vector) o;
      return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
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
