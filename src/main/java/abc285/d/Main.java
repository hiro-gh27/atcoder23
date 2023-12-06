package abc285.d;

import java.io.*;
import java.util.ArrayList;
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


  class UnionFind{
    int parent[];

    HashMap<String, Integer> map;

    public UnionFind(HashSet<String> uniqueList) {
      parent = new int[uniqueList.size()];
      map = new HashMap<>();

      int index = 0;
      for (String key : uniqueList) {
        parent[index] = index;
        map.put(key, index);
        index++;
      }

    }

    public int root(int target){
      if (parent[target] == target){
        return parent[target];
      }
      parent[target] = root(parent[target]);
      return parent[target];
    }


    boolean push(String strX, String strY){
      int x = root(map.get(strX));
      int y = root(map.get(strY));

      if (x == y){
        return false;
      }

      parent[x] = parent[y];
      return true;

    }


  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int numberOfUsers = sc.nextInt();

    HashSet<String> unique = new HashSet<>();
    HashMap<String, String> orders = new HashMap<>();
    for (int i = 0; i < numberOfUsers; i++) {
      String from = sc.next();
      String to = sc.next();
      unique.add(from);
      unique.add(to);
      orders.put(from, to);
    }

    UnionFind uTree = new UnionFind(unique);
    for (Entry<String, String> theOrder : orders.entrySet()) {
      boolean result = uTree.push(theOrder.getKey(), theOrder.getValue());
      if (!result){
        out.println("No");
        return;
      }
    }

    out.println("Yes");


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
