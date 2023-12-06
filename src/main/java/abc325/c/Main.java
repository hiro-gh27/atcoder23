package abc325.c;

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

  class Resolver{
    char[][] map;

    public Resolver(char[][] map) {
      this.map = map;
    }

    char get(int x, int y){
      if (x<0 || y < 0 || x >= map.length || y >= map[x].length) return '.';
      return map[x][y];
    }

    void remove(int x, int y){
      map[x][y] = '.';
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int H = sc.nextInt();
    int W = sc.nextInt();
    char[][] dpsMap = new char[H][W];
    for (int i = 0; i < H; i++) {
      dpsMap[i] = sc.next().toCharArray();
    }
    Resolver resolver = new Resolver(dpsMap);

    ArrayList<Point> vectorLit = new ArrayList<>();
    vectorLit.add(new Point(1, 1));
    vectorLit.add(new Point(0, 1));
    vectorLit.add(new Point(1, 0));
    vectorLit.add(new Point(1, -1));
    vectorLit.add(new Point(-1, 1));
    vectorLit.add(new Point(-1, 0));
    vectorLit.add(new Point(0, -1));
    vectorLit.add(new Point(-1, -1));

    int ans = 0;
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (resolver.get(i,j)=='#'){
          ans++;
        }else{
          continue;
        }

        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(i, j));
        while (!deque.isEmpty()){
          Point current = deque.poll();
          int x = current.x;
          int y = current.y;
          resolver.remove(x,y);

          for (Point vector : vectorLit) {
            if (resolver.get(x+vector.x, y+vector.y) == '#'){
              resolver.remove(x+vector.x, y+ vector.y);
              deque.add(new Point(x+vector.x, y+vector.y));
            }
          }
          
        }
      }
    }
    out.println(ans);
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
