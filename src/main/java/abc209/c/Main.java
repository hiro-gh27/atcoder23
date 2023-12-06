package abc209.c;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
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

  class Graph{
    ArrayList<ArrayList<Integer>> inner;
    ArrayList<Integer> color;

    public Graph(int node) {
      inner = new ArrayList<>();
      color = new ArrayList<>();
      for (int i = 0; i < node+1; i++) {
        inner.add(new ArrayList<>());
        color.add(-1);
      }
    }

    public void addBridge(int from, int to){
      inner.get(from).add(to);
      inner.get(to).add(from);
    }

    public boolean paintTwoColor(int initNode){
      HashSet<Integer> visited = new HashSet<>();
      ArrayDeque<Integer> deque = new ArrayDeque<>();
      color.set(initNode, 0);
      deque.add(initNode);

      while (!deque.isEmpty()){
        int node = deque.poll();
        visited.add(node);

        ArrayList<Integer> adjacentNodeList = inner.get(node);
        int nextColor = color.get(node) == 1 ? 0 : 1;
        for (Integer adjacentNode : adjacentNodeList) {
          // 既に塗られている場合
          if (visited.contains(adjacentNode)) continue;
          if (color.get(adjacentNode) != -1) return false;
          color.set(adjacentNode, nextColor);
          deque.add(adjacentNode);
        }
      }
      return true;
    }

    public int getColor(int node){
      return color.get(node);
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int Q = sc.nextInt();
    Graph graph = new Graph(N);

    for (int i = 0; i < N-1; i++) {
      int c = sc.nextInt();
      int d = sc.nextInt();
      graph.addBridge(c, d);
    }

    boolean canPaint = graph.paintTwoColor(1);

    for (int i = 0; i < Q; i++) {
      int c = sc.nextInt();
      int d = sc.nextInt();

      int colorC = graph.getColor(c);
      int colorD = graph.getColor(d);

      String queryAns = colorC == colorD ? "Town" : "Road";
      out.println(queryAns);
    }
    
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
