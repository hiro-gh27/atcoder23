package arc006.b;

import java.awt.Point;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
    int L = sc.nextInt();

    char[][] graph = new char[L+2][2*N-1];
    for (int i = 0; i < N; i++) {
      graph[0][2*i] = (char) (i + '0');
    }

    for (int i = 1; i < graph.length; i++) {
      char[] debug =  sc.nextLine().toCharArray();
      graph[i] = debug;
    }

    ArrayDeque<Point> next = new ArrayDeque<>();
    char[] last = graph[graph.length-1];
    for (int i = 0; i < last.length; i++) {
      if (last[i] == 'o'){
        next.add(new Point(graph.length-1, i));
      }
    }

    ArrayList<Point> vectorsList = new ArrayList<>();
    vectorsList.add(new Point(-1, 0));
    vectorsList.add(new Point(0, -1));
    vectorsList.add(new Point(0, 1));

    char ans = 0;
    int count = 0;
    while (!next.isEmpty()){
      Point node = next.poll();

      graph[node.x][node.y] = (char)(count + 'A');
      count++;

      ArrayList<Point> goSide = new ArrayList<>();
      ArrayList<Point> goVertical = new ArrayList<>();

      for (Point vector : vectorsList) {
        int nextX = node.x + vector.x;
        int nextY = node.y + vector.y;
        if (nextX >= 0 && nextX < graph.length - 1) {
          if (nextY >= 0 && nextY < graph[0].length) {
            char value = graph[nextX][nextY];
            if (value == '-') {
              goSide.add(new Point(nextX, nextY));
            }else if (value == '|' ){
              goVertical.add(new Point(nextX, nextY));
            } else if (value >= '0' && value <= '9') {
              ans = value;
              int v = ans - '0';
              v++;
              out.println((char) (v + '0'));
              return;
            }
          }
        }
      }

      if (goSide.size()>0){
        next.addAll(goSide);
      }else {
        next.addAll(goVertical);
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
