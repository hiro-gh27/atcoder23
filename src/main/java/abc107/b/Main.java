package abc107.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    int H = sc.nextInt();
    int W = sc.nextInt();
    char[][] map = new char[H][W];

    for (int i = 0; i < H; i++) {
      map[i] = sc.next().toCharArray();
    }

    for (int i = 0; i < map.length; i++) {
      boolean replaceable = true;
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != '.' && map[i][j] != '*'){
          replaceable = false;
          break;
        }
      }
      if (replaceable){
        Arrays.fill(map[i], '*');
      }
    }

    for (int i = 0; i < W; i++) {
      boolean replaceable = true;
      for (int j = 0; j < H; j++) {
        if (map[j][i] != '.' && map[j][i] != '*'){
          replaceable = false;
          break;
        }
      }
      if (replaceable){
        for (int j = 0; j < H; j++) {
          map[j][i] = '*';
        }
      }
    }

    for (char[] outer : map) {
      StringBuilder sb = new StringBuilder();
      for (char inner : outer) {
        if (inner != '*'){
          sb.append(inner);
        }
      }
      if (sb.length()>0){
        out.println(sb.toString());
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
