package arc104.b;

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
    char[] letters = sc.next().toCharArray();

    int ans = 0;
    for (int i = 0; i < letters.length; i++) {
      Counter counter = new Counter();
      counter.increment(letters[i]);
      for (int j = i+1; j < letters.length; j++) {
        counter.increment(letters[j]);
        if (counter.isComplementarity()) ans++;
      }
    }

    out.println(ans);
  }

  class Counter{
    private int a = 0;
    private int t = 0;
    private int c = 0;
    private int g = 0;

    public Counter() {
    }

    void increment(char target){
      switch (target){
        case 'A':
          a++;
          break;
        case 'T':
          t++;
          break;
        case 'C':
          c++;
          break;
        case 'G':
          g++;
          break;
      }
    }

    boolean isComplementarity(){
      return (a == t && c ==g);
    }
  }


  private boolean checkATCG(char a, char b){
    if (a == 'A' && b == 'T') return true;
    if (a == 'T' && b == 'A') return true;
    if (a == 'C' && b == 'G') return true;
    if (a == 'G' && b == 'C') return true;

    return false;
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
