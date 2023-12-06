package abl.c;

import java.io.*;
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
    int M = sc.nextInt();

    UnionFind unionFind = new UnionFind(N);
    for (int i = 0; i < M; i++) {
      int a = sc.nextInt() - 1;
      int b = sc.nextInt() - 1;
      unionFind.union(a,b);
    }

    HashSet<Integer> uniq = new HashSet<>();
    for (int i = 0; i < N; i++) {
      uniq.add(unionFind.root(i));
    }
    out.println(uniq.size()-1);
  }

  class UnionFind{
    int[] parent;
    int[] treeSize;

    UnionFind(int size){
      parent = new int[size];
      treeSize = new int[size];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = i;
        treeSize[i] = 1;
      }
    }
    int root(int x) {
      if (parent[x] == x) return x;
      parent[x] = root(parent[x]);
      return parent[x];
    }

    boolean union(int x, int y){
      x = root(x);
      y = root(y);
      if (x == y) return false;

      if (treeSize[x] < treeSize[y]){
        parent[x] = y;
        treeSize[y] += treeSize[x];
      }else {
        parent[y] = x;
        treeSize[x] += treeSize[y];
        if (treeSize[x] == treeSize[y]){
          treeSize[x] += 1;
        }
      }
       return true;
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
