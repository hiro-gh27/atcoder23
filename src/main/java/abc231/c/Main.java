package abc231.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.stream.Collectors;

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
    int Q = sc.nextInt();
    int[] A = new int[N];
    int[] X = new int[Q];

    for (int i = 0; i < A.length; i++) {
      A[i] = sc.nextInt();
    }
    for (int i = 0; i < X.length; i++) {
      X[i] = sc.nextInt();
    }
    int[] XCopied = Arrays.copyOf(X, X.length);

    Arrays.sort(A);
    Arrays.sort(XCopied);

    int indexA = 0;
    HashMap<Integer, Integer> ans = new HashMap<>();
    for (int i = 0; i < XCopied.length; i++) {
      for (int j = indexA; j < A.length; j++) {
        if (A[j] >= XCopied[i]){
          ans.put(XCopied[i], A.length - j);
          break;
        }
        indexA = j;
      }
    }

    for (int i = 0; i < X.length; i++) {
      if (ans.containsKey(X[i])){
        out.println(ans.get(X[i]));
      }else {
        out.println(0);
      }
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
