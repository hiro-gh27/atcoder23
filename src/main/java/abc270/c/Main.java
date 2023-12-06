package abc270.c;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.stream.Collectors;

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
    HashMap<Integer, ArrayList<Integer>> edges;

    public Graph(int node) {
      this.edges = new HashMap<>();
      for (int i = 0; i < node + 1; i++) {
        edges.put(i, new ArrayList<>());
      }
    }

    public void addEdge(int from, int to){
      edges.get(from).add(to);
      edges.get(to).add(from);
    }

    public ArrayList<Integer> adjacent(int node){
      return edges.get(node);
    }

  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int X = sc.nextInt();
    int Y = sc.nextInt();

    Graph graph = new Graph(N);

    while (true){
      try {
        int src = sc.nextInt();
        int dest = sc.nextInt();
        graph.addEdge(src, dest);
      }catch (Exception e){
        break;
      }
    }

    int[] parent = new int[N+1];
    ArrayDeque<Integer> deque = new ArrayDeque<>();
    deque.add(X);
    HashSet<Integer> visited = new HashSet<>();

    while (!deque.isEmpty()){
      int node = deque.poll();
      ArrayList<Integer> adjacentList = graph.adjacent(node);

      for (Integer adjacent : adjacentList) {
        if (visited.contains(adjacent)) continue;

        visited.add(node);
        parent[adjacent] = node;

        if (adjacent == Y) {
          deque = new ArrayDeque<>();
          break;
        }
        
        deque.add(adjacent);
      }
    }


    ArrayList<Integer> history = new ArrayList<>();
    int current = Y;
    while (current != X){
      history.add(current);
      current = parent[current];
    }

    history.add(X);
    Collections.reverse(history);
    out.println(history.stream().map(String::valueOf).collect(Collectors.joining(" ")));


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
