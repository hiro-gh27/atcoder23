package abc312.b;

import java.io.*;
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

  class Graph {
    char[][] inner;

    public Graph(char[][] inner) {
      this.inner = inner;
    }

    char getOrDefault(int x, int y, char defaultValue){
      if (x < 0 || x >= inner.length || y < 0 || y >= inner[x].length){
        return defaultValue;
      }
      return inner[x][y];
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int M = sc.nextInt();
    char[][] inputs = new char[N][M];
    for (int i = 0; i < inputs.length; i++) {
      inputs[i] = sc.next().toCharArray();
    }

    char[][] left = new char[4][4];
    left[0] = "###.".toCharArray();
    left[1] = "###.".toCharArray();
    left[2] = "###.".toCharArray();
    left[3] = "....".toCharArray();

    char[][] right = new char[4][4];
    right[0] = "....".toCharArray();
    right[1] = ".###".toCharArray();
    right[2] = ".###".toCharArray();
    right[3] = ".###".toCharArray();


    Graph graph = new Graph(inputs);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {

        boolean isLeftMatched = true;
        for (int k = 0; k < 4; k++) {
          for (int l = 0; l < 4; l++) {
            if (graph.getOrDefault(i+k, j+l, ' ') != left[k][l]){
              isLeftMatched = false;
            }
          }
        }

        boolean isRightMatched = true;
        for (int k = 0; k < 4; k++) {
          for (int l = 0; l < 4; l++) {
            int kk = k + 5;
            int ll = l + 5;
            if (graph.getOrDefault(i+kk, j+ll, ' ') != right[k][l]){
              isRightMatched = false;
            }
          }
        }

        if (isLeftMatched && isRightMatched) {
          StringBuilder sb = new StringBuilder().append(i+1).append(" ").append(j+1);
          out.println(sb);
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
