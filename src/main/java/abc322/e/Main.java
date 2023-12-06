package abc322.e;

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

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int K = sc.nextInt();
    int P = sc.nextInt();

    long[][] params = new long[N][K+1];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < params[i].length; j++) {
        params[i][j] = sc.nextLong();
      }
    }


    String last = "";
    long ans = Long.MAX_VALUE;
    for (int i = 0; i < Math.pow(N,2) ; i++) {
      long[] result = new long[K+1];
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < N; j++) {
        if ((i>>j & 1) == 1 ){
          sb.append(1);
          for (int k = 0; k < params[j].length; k++) {
            result[k]+= params[j][k];
          }
        }else {
          sb.append(0);
        }
      }

      LOGGER.info(sb.toString());
      last = sb.toString();
      boolean ok = true;
      for (int m = 1; m < result.length; m++) {
        if (result[m]<P){
          ok = false;
          break;
        }
      }
      if (ok){
        ans = Math.min(result[0], ans);
      }
    }
    if (ans == Long.MAX_VALUE){
      ans = -1;
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
