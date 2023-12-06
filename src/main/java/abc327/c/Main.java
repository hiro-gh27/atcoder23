package abc327.c;

import java.awt.Point;
import java.io.*;
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
    int[][] numberPlace = new int[9][9];
    for (int i = 0; i < numberPlace.length; i++) {
      for (int j = 0; j < numberPlace[i].length; j++) {
        numberPlace[i][j] = sc.nextInt();
      }
    }
    for (int i = 0; i < numberPlace.length; i++) {
      HashSet<Integer> horizontalDuplicate = new HashSet<>();
      for (int j = 0; j < numberPlace[i].length; j++) {
        if (horizontalDuplicate.contains(numberPlace[i][j])){
          out.println("No");
          return;
        }
        horizontalDuplicate.add(numberPlace[i][j]);
      }
    }

    for (int i = 0; i < 9; i++) {
      HashSet<Integer> verticalDuplicate = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        if (verticalDuplicate.contains(numberPlace[j][i])){
          out.println("No");
          return;
        }
        verticalDuplicate.add(numberPlace[j][i]);
      }
    }

    ArrayList<Point> vectorsList = new ArrayList<>();
    int[] src = new int[]{-1, 0, 1};
    for (int i = 0; i < src.length; i++) {
      for (int j = 0; j < src.length; j++) {
        vectorsList.add(new Point(src[i], src[j]));
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int x = i*3+1;
        int y = j*3+1;
        HashSet<Integer> duplicate = new HashSet<>();
        for (Point vector : vectorsList) {
          if (duplicate.contains(numberPlace[x+vector.x][y+vector.y])){
            out.println("No");
            return;
          }
          duplicate.add(numberPlace[x+vector.x][y+vector.y]);
        }
      }
    }

    out.println("Yes");
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
