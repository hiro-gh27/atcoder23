package abc160.d;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

  class GraphManager {

    HashMap<Integer, HashSet<Integer>> graph;

    // formNodeId : (costId: numOfNode)
    HashMap<Integer, Integer> result;

    public GraphManager(int size, int shortcutLinkFrom, int shortcutLinkTo) {
      // グラフを初期化
      graph = new HashMap<>();
      for (int i = 1; i < size; i++) {
        HashSet<Integer> go = graph.getOrDefault(i, new HashSet<>());
        HashSet<Integer> back = graph.getOrDefault(i+1, new HashSet<>());
        go.add(i+1);
        back.add(i);
        graph.put(i, go);
        graph.put(i+1, back);
      }
      graph.get(shortcutLinkFrom).add(shortcutLinkTo);

      result = new HashMap<>();
    }

    class Node {
      int id;
      int cost;

      public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
      }
    }

    public void countCostToEndNode(int start){
      ArrayDeque<Node> nodeQueue = new ArrayDeque<>();
      HashMap<Integer, Integer> costIndex = new HashMap<>();
      HashSet<Integer> visited = new HashSet<>();

      visited.add(start);
      nodeQueue.add(new Node(start, 0));


      while (!nodeQueue.isEmpty()){
        Node node = nodeQueue.poll();
        HashSet<Integer> connectedNodesSet = graph.getOrDefault(node.id, new HashSet<>());

        for (Integer connectedNodeId : connectedNodesSet) {
          Node nextNode = new Node(connectedNodeId, node.cost + 1);
          if (!visited.contains(nextNode.id)){
            int currentValue = costIndex.getOrDefault(nextNode.id, Integer.MAX_VALUE);
            costIndex.put(nextNode.id, Math.min(currentValue, nextNode.cost));
            visited.add(nextNode.id);
            nodeQueue.add(nextNode);
          }
        }
      }

      // 計上
      for (Entry<Integer, Integer> indexs : costIndex.entrySet()) {
        int overallValue = result.getOrDefault(indexs.getValue(), 0);
        if (indexs.getKey() > start){
          result.put(indexs.getValue(), overallValue+1);
        }
      }
    }

    public void printResult(){
      for (int i = 1; i < graph.size(); i++) {
        int value = result.getOrDefault(i, 0);
        out.println(value);
      }
    }



    @Override
    public String toString() {
      return "GraphManager{" +
          "graph=" + graph +
          ", result=" + result +
          '}';
    }
  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int numberOfNodes = sc.nextInt();
    int nodeFrom = sc.nextInt();
    int nodeTo = sc.nextInt();

    GraphManager gm = new GraphManager(numberOfNodes, nodeFrom, nodeTo);

    for (int i = 0; i < numberOfNodes; i++) {
      gm.countCostToEndNode(i+1);
    }
    gm.printResult();

    LOGGER.info(gm.toString());
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
