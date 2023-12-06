package abc199.c;

import java.io.*;
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
    int N = sc.nextInt();
    char[] inputs = sc.nextLine().toCharArray();
    char[] left = new char[N];
    char[] right = new char[N];
    System.arraycopy(inputs, 0, left, 0, N);
    System.arraycopy(inputs, N, right, 0, N);

    int numberOfQuery = sc.nextInt();


    Letters letters = new Letters(left, right);

    for (int i = 0; i < numberOfQuery; i++) {
      int t = sc.nextInt();
      int a = sc.nextInt() - 1;
      int b = sc.nextInt() - 1;

      switch (t){
        case 1:
          letters.swap(a,b);
          break;
        case 2:
          letters.swap();
          break;
      }
    }
    out.println(letters.result());
  }

  class Letters{
    char[] left;
    char[] right;

    public Letters(char[] left, char[] right) {
      this.left = left;
      this.right = right;
    }

    char get(int n){
      if (n < left.length){
        return left[n];
      }else {
        return right[n - left.length];
      }
    }

    void set(int n, char c){
      if (n < left.length){
        left[n] = c;
      }else {
        right[n - left.length] = c;
      }
    }

    void swap(){
      char[] tmp;
      tmp = right;
      right = left;
      left = tmp;
    }

    void swap(int a, int b) {
      char val1 = get(a);
      char val2 = get(b);
      set(a, val2);
      set(b, val1);
    }
    String result(){
      char[] result = new char[left.length + right.length];
      System.arraycopy(left,0, result, 0, left.length);
      System.arraycopy(right, 0, result, left.length, right.length);
      return new String(result);
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
