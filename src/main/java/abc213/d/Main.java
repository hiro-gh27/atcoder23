package abc213.d;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
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

  public static HashMap<Integer, PriorityQueue<Integer>> graph = new HashMap<>();
  public static ArrayList<String> history = new ArrayList<>();
  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    for (int i = 0; i < N-1; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      addQueue(a, b);
      addQueue(b, a);
    }
    dfs(1, -1);
    out.println(String.join(" ", history));
  }

  public void dfs(int current, int previous){
    PriorityQueue<Integer> queue = graph.get(current);
    history.add(String.valueOf(current));

    while (!queue.isEmpty()){
      int next = queue.poll();
      if (next != previous){
        dfs(next, current);
        history.add(String.valueOf(current));
      }
    }
  }

  private void addQueue(int key, int value) {
    if (graph.containsKey(key)) {
      graph.get(key).add(value);
    } else {
      PriorityQueue<Integer> queue = new PriorityQueue<>();
      queue.add(value);
      graph.put(key, queue);
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
