package abc322.d;

import java.awt.Point;
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

  char[][] readParts(MyScanner sc){
    char[][] parts = new char[4][4];
    for (int i = 0; i < 4; i++) {
      parts[i] = sc.next().toCharArray();
    }
    return parts;
  }

  char[][] partsRotation(char[][] parts){
    char[][] result = new char[4][4];
    for (int i = 0; i < parts.length; i++) {
      for (int j = 0; j < parts[i].length; j++) {
        result[j][result.length-1-i] = parts[i][j];
      }
    }
    return result;
  }

  char[][] entrypointMarking(char[][] parts) {
    for (int i = 0; i < parts.length; i++) {
      for (int j = 0; j < parts[i].length; j++) {
        if (parts[i][j] == '#'){
          parts[i][j] = '*';
          return parts;
        }
      }
    }
    return parts;
  }

  class GameBord {
    char[][] bord = new char[12][12];

    boolean fill(char[][] parts, int x , int y){
      Point partsLandmark = findPoint(parts);
      for (int i = 0; i < parts.length; i++) {
        for (int j = 0; j < parts[i].length; j++) {
          bord[4+x-partsLandmark.x+i][4+y-partsLandmark.y+j] = parts[i][j];
          if (parts[i][j] == '#' || parts[i][j] == '*'){
            if (i < 4 || j < 4 || i > 7 | j > 7) return false;
          }
        }
      }
      return true;
    }

    private Point findPoint(char[][] parts){
      for (int i = 0; i < parts.length; i++) {
        for (int j = 0; j < parts[i].length; j++) {
          if (parts[i][j] == '*'){
            return new Point(i,j);
          }
        }
      }
      return new Point();
    }

  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();

    char[][] parts1 = entrypointMarking(readParts(sc));
    char[][] parts2 = entrypointMarking(readParts(sc));
    char[][] parts3 = entrypointMarking(readParts(sc));

    LOGGER.info(Arrays.deepToString(parts1));
    var next = partsRotation(parts1);
    out.println(Arrays.deepToString(next));

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        
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
