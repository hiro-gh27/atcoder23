package abc327.d;

import java.io.*;
import java.util.ArrayDeque;
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
    int M = sc.nextInt();
    int[][] numbers = new int[2][M];
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers[i].length; j++) {
        numbers[i][j] = sc.nextInt();
      }
    }

    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    for (int i = 0; i < M; i++) {
      int from = numbers[0][i];
      int to = numbers[1][i];

      HashSet<Integer> firstSet = graph.getOrDefault(from, new HashSet<>());
      firstSet.add(to);
      graph.put(from, firstSet);

      HashSet<Integer> secondSet = graph.getOrDefault(to, new HashSet<>());
      secondSet.add(from);
      graph.put(to, secondSet);
    }

    int entrypoint = numbers[0][0];
    GraphPainter gp = new GraphPainter(graph);
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers[i].length; j++) {
        if (!gp.paint(numbers[i][j])){
          out.println("No");
          return;
        }
      }
    }
    out.println("Yes");
  }

  class GraphPainter{
    HashMap<Integer, Integer> color = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    public GraphPainter(HashMap<Integer, HashSet<Integer>> graph) {
      this.graph = graph;
    }

    boolean paint(int entrypoint){
      if (color.containsKey(entrypoint)) return true;

      ArrayDeque<Integer> nextNodes = new ArrayDeque<>();
      nextNodes.add(entrypoint);
      color.put(entrypoint, 0);

      while (!nextNodes.isEmpty()){
        int currentNodes = nextNodes.poll();
        int currentColor = color.get(currentNodes);

        HashSet<Integer> adjacentNodes = graph.getOrDefault(currentNodes, new HashSet<>());
        for (Integer adjacentNode : adjacentNodes) {
          int adjacentColor = color.getOrDefault(adjacentNode, -1);
          if (currentColor == adjacentColor){
            return false;
          }
          if (adjacentColor != -1){
            continue;
          }
          color.put(adjacentNode, currentColor == 0 ? 1:0);
          nextNodes.add(adjacentNode);
        }

      }
      return true;
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
