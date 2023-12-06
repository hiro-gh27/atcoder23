package abc313.b;

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

    HashMap<Integer, ArrayList<Integer>> weakToStrongMap = new HashMap<>();
    for (int i = 0; i < M; i++) {
      int stronger = sc.nextInt();
      int weaker = sc.nextInt();
      ArrayList<Integer> strongerList = weakToStrongMap.getOrDefault(weaker, new ArrayList<>());
      strongerList.add(stronger);
      weakToStrongMap.put(weaker, strongerList);
    }

    HashSet<Integer> terminate = new HashSet<>();
    for (int i = 1; i <= N; i++) {
      ArrayDeque<Integer> available = new ArrayDeque<>();
      HashSet<Integer> visited = new HashSet<>(i);
      available.add(i);
      if (!available.isEmpty()) {
        int currentNode = available.poll();
        visited.add(currentNode);

        ArrayList<Integer> connectedNode = weakToStrongMap.getOrDefault(currentNode, new ArrayList<>());
        if (connectedNode.isEmpty()){
          terminate.add(currentNode);
        }else {
          for (Integer connect : connectedNode) {
            if (!visited.contains(connect)) {
              available.add(connect);
            }
          }
        }
      }
    }

    if (terminate.size()==1) out.println(terminate.stream().findFirst().orElseGet(()->-1));
    else out.println(-1);

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
