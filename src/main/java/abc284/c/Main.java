package abc284.c;

import java.awt.Point;
import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  private static PrintWriter out;

  public static void main(String[] args) {
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

    boolean[][] table = new boolean[N+1][N+1];
    boolean[] checked = new boolean[N+1];

    for (int i = 0; i < M; i++) {
      int v1 = sc.nextInt();
      int v2 = sc.nextInt();
      table[v1][v2] = true;
      table[v2][v1] = true;
    }

    int ans = 0;
    for (int i = 1; i < table.length; i++) {
      for (int j = 1; j < table[i].length; j++) {
        ArrayDeque<Point> deque = new ArrayDeque<>();

        // entrypoint
        if (table[i][j]){
          ans++;
          Point p = markAsVisitedAndCreatePoint(i, j, table, checked);
          deque.add(new Point(i,j));
        }
        
        while (!deque.isEmpty()){
          Point point = deque.pop();

          for (int k = 0; k < table[point.x].length; k++) {
            if (table[point.x][k]){
              Point p = markAsVisitedAndCreatePoint(point.x, k, table, checked);
              deque.add(p);
            }
          }

          for (int k = 0; k < table[point.y].length; k++) {
            if (table[point.y][k]){
              Point p = markAsVisitedAndCreatePoint(point.y, k, table, checked);
              deque.add(p);
            }
          }
        }
      }
    }

    for (int i = 1; i < checked.length; i++) {
      if (!checked[i]){
        ans++;
      }

    }

    out.println(ans);

  }

  private Point markAsVisitedAndCreatePoint(int i, int j, boolean[][] adjacencyMatrix, boolean[] visitedNode) {
    adjacencyMatrix[i][j] = false;
    adjacencyMatrix[j][i] = false;
    visitedNode[i] = true;
    visitedNode[j] = true;
    return new Point(i,j);
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
