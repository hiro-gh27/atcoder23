package abc311.c;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    HashMap<Integer, HashSet<Integer>> routes = new HashMap<>();
    for (int i = 1; i < N+1; i++) {
      int A = sc.nextInt();
      var route = routes.getOrDefault(i, new HashSet<>());
      route.add(A);
      routes.put(i, route);
    }

    int[] visited = new int[N+1];
    for (int i = 1; i < N + 1; i++) {
      ArrayDeque<Integer> deque = new ArrayDeque<>(routes.get(i));
      ArrayList<Integer> history = new ArrayList<>();

      while (!deque.isEmpty()){
        int currentNode = deque.poll();
        history.add(currentNode);
        visited[currentNode] = i;
        for (Integer nextNode : routes.get(currentNode)) {
          if (visited[nextNode] == i){
            ArrayList<Integer> ans = new ArrayList<>();
            for (int j = history.size() - 1; j >= 0; j--) {
              ans.add(history.get(j));
              if (history.get(j).equals(nextNode)){
                break;
              }
            }
            Collections.reverse(ans);
            out.println(ans.size());
            String line = ans.stream().map(String::valueOf).collect(Collectors.joining(" "));
            out.println(line);
            return;
          }else if(visited[nextNode] != 0) {
            deque = new ArrayDeque<>();
          }else {
            deque.add(nextNode);
          }

        }
      }

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
