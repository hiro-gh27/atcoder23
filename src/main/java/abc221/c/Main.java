package abc221.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    char[] cLetters = sc.nextLine().toCharArray();
    String[] letters = new String[cLetters.length];
    for (int i = 0; i < letters.length; i++) {
      letters[i] = String.valueOf(cLetters[i]);
    }
    Arrays.sort(letters, Comparator.reverseOrder());


    ArrayList<String> left = new ArrayList<>();
    ArrayList<String> right = new ArrayList<>();
    left.add(letters[0]);
    right.add(letters[1]);

    for (int i = 2; i < letters.length; i++) {
      String leftStr = String.join("", left);
      String rightStr = String.join("", right);

      long oLeft = Long.parseLong(leftStr);
      long oRight = Long.parseLong(rightStr);

      long leftAdd = Long.parseLong(leftStr + letters[i]);
      long rightAdd = Long.parseLong(rightStr + letters[i]);

      if (leftAdd * oRight > rightAdd * oLeft){
        left.add(letters[i]);
      }else {
        right.add(letters[i]);
      }
    }

    String leftStr = String.join("", left);
    String rightStr = String.join("", right);
    long valLeft = Long.parseLong(leftStr);
    long valRight = Long.parseLong(rightStr);

    out.println(valLeft * valRight);

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
