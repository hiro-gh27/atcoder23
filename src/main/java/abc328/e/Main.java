package abc328.e;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
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

  class Edge implements Comparable<Edge>{
    int from;
    int to;
    Long wight;

    public Edge(int from, int to, Long wight) {
      this.from = from;
      this.to = to;
      this.wight = wight;
    }

    @Override
    public int compareTo(Edge o) {
      return wight.compareTo(o.wight);
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int M = sc.nextInt();
    long K = sc.nextLong();
    long[][] graph = new long[N+1][N+1];
    for (int i = 0; i < graph.length; i++) {
      Arrays.fill(graph[i], Long.MAX_VALUE);
    }

    int x = 0;
    int y = 0;
    for (int i = 0; i < M; i++) {
      x = sc.nextInt();
      y = sc.nextInt();
      long w = sc.nextLong();
      graph[x][y] = Math.min(graph[x][y], w);
      graph[y][x] = Math.min(graph[y][x], w);
    }

    long min = Long.MAX_VALUE;
    for (int z = 1; z <= N; z++) {
      x = z;

      PriorityQueue<Edge> edgesQueue = new PriorityQueue<>();
      HashSet<Integer> visited = new HashSet<>();
      visited.add(x); // TODO: 本当に??

      for (int i = 0; i < graph[x].length; i++) {
        long wight = graph[x][i];
        if (wight != Long.MAX_VALUE) edgesQueue.add(new Edge(x, i, wight));
      }

      long totalWight = 0;
      while (!edgesQueue.isEmpty() && visited.size() < N){
        Edge next = edgesQueue.poll();
        if (visited.contains(next.to)) continue;

        visited.add(next.to);
        totalWight += (next.wight)%K;
        totalWight %= K;

        for (int i = 0; i < graph[next.to].length; i++) {
          long wight = graph[next.to][i];
          if (wight != Long.MAX_VALUE) edgesQueue.add(new Edge(next.to, i, wight));
        }
      }
      min = Math.min(min, totalWight);
    }

    out.println(min % K);
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
