package abc157.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

  class Condition{
    int index;
    int digit;

    public Condition(int index, int digit) {
      this.index = index;
      this.digit = digit;
    }

  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int numberOfDigits = sc.nextInt();
    int numberOfConditions = sc.nextInt();

    ArrayList<Condition> conditions = new ArrayList<>();
    for (int i = 0; i < numberOfConditions; i++) {
      conditions.add(new Condition(sc.nextInt(), sc.nextInt()));
    }

    String ans = "-1";
    for (int i = 0; i <= 1000; i++) {
      String strI = String.valueOf(i);
      if (strI.length() != numberOfDigits){
        continue;
      }

      char[] letters = strI.toCharArray();
      boolean found = true;
      for (Condition condition : conditions) {
        if (letters[condition.index - 1] != condition.digit + '0'){
          found = false;
          break;
        }
      }

      if (found){
        ans = strI;
        break;
      }
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
