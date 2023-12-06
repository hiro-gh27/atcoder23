package abc309.d;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
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

  class Graph {
    private HashMap<Integer, List<Integer>> adjList;

    public Graph(int nodes){
      adjList = new HashMap<>();
      for (int i = 1; i <= nodes; i++) {
        adjList.put(i, new ArrayList<>());
      }
    }

    public void addEdge(int src, int dest){
      adjList.get(src).add(dest);
      adjList.get(dest).add(src);
    }

    public List<Integer> getAdjacentList(int id){
      return adjList.get(id);
    }
  }

  private void run(String[] arguments) throws Exception {

    MyScanner sc = new MyScanner();
    int N1 = sc.nextInt();
    int N2 = sc.nextInt();
    int M = sc.nextInt();
    Graph graph = new Graph(N1+N2);

    for (int i = 0; i < M; i++) {
      int src = sc.nextInt();
      int dest = sc.nextInt();
      graph.addEdge(src, dest);
    }

    int max1 = 0;
    for (Entry<Integer, Integer> integerEntry : getHistory(graph, 1).entrySet()) {
      max1 = Math.max(max1, integerEntry.getValue());
    }
    int max2 = 0;
    for (Entry<Integer, Integer> integerEntry : getHistory(graph, N1 + N2).entrySet()) {
      max2 = Math.max(max2, integerEntry.getValue());
    }

    out.println(max1 + max2 + 1);


  }

  private HashMap<Integer, Integer> getHistory(Graph graph, int start){
    ArrayDeque<Integer> deque = new ArrayDeque<>();
    deque.add(start);
    HashMap<Integer, Integer> arrivedHistory = new HashMap<>();
    arrivedHistory.put(start, 0);

    while (!deque.isEmpty()){
      int node = deque.poll();
      List<Integer> ajacents = graph.getAdjacentList(node);
      for (Integer ajacent : ajacents) {
        if (arrivedHistory.containsKey(ajacent)){
          continue;
        }
        int value = arrivedHistory.get(node);
        arrivedHistory.put(ajacent, value+1);
        deque.add(ajacent);
      }
    }
    return arrivedHistory;
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
