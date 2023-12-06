package abc293.c;

import java.awt.Point;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    int H = sc.nextInt();
    int W = sc.nextInt();

    int[][] table = new int[H+2][W+2];

    for (int i = 1; i < H+1; i++) {
      for (int j = 1; j < W+1; j++) {
        table[i][j] = sc.nextInt();
      }
    }

    Point p = new Point(1,1);
    ArrayList<Integer> h = new ArrayList<>(List.of(table[1][1]));
    PositionHistory ph = new PositionHistory(p, h);
    ArrayDeque<PositionHistory> queue = new ArrayDeque<>();
    queue.add(ph);

    ArrayList<PositionHistory> results = new ArrayList<>();
    while (!queue.isEmpty()){
      PositionHistory currentPositionHistory = queue.poll();
      Point currentPoint = currentPositionHistory.point;
      ArrayList currentHistory = currentPositionHistory.history;

      if (currentPoint.x == H && currentPoint.y == W){
        results.add(currentPositionHistory);
        continue;
      }

      // down
      if (table[currentPoint.x+1][currentPoint.y] != 0){
        ArrayList<Integer> copied = (ArrayList) currentHistory.clone();
        copied.add(table[currentPoint.x+1][currentPoint.y]);
        queue.add(new PositionHistory(new Point(currentPoint.x+1,currentPoint.y), copied));
      }

      // right
      if (table[currentPoint.x][currentPoint.y+1] != 0){
        ArrayList<Integer> copied = (ArrayList) currentHistory.clone();
        copied.add(table[currentPoint.x][currentPoint.y+1]);
        queue.add(new PositionHistory(new Point(currentPoint.x,currentPoint.y+1), copied));
      }
    }

    int ans = 0;
    for (PositionHistory result : results) {
      HashSet<Integer> set = new HashSet<>(result.history);
      if (set.size() == result.history.size()) ans++;
    }


    out.println(ans);


  }

  class PositionHistory implements Cloneable{
    Point point;
    ArrayList<Integer> history;

    public PositionHistory(Point point, ArrayList<Integer> history) {
      this.point = point;
      this.history = history;
    }

    public void addHistory(int number){
      history.add(number);
    }
    public void updatePosition(Point point){
      this.point = point;
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
