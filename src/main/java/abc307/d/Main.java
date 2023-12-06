package abc307.d;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
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
    char[] inputs = sc.next().toCharArray();
    boolean[] delete = new boolean[inputs.length];

    Stack<Integer> leftBracketsIndex = new Stack<>();

    for (int i = 0; i < inputs.length; i++) {
      if (inputs[i] == '('){
        leftBracketsIndex.push(i);
      }

      if (inputs[i] == ')'){
        if (leftBracketsIndex.isEmpty()) continue;

        int left = leftBracketsIndex.pop();
        int right = i;
        Arrays.fill(delete, left, right+1, true);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < inputs.length; i++) {
      if (delete[i]) continue;
      sb.append(inputs[i]);
    }
    out.println(sb.toString());

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
