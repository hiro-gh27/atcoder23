package arc106.a;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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
    long N = sc.nextLong();

    HashMap<Integer, Long> powerOfThree = new HashMap<>();
    HashMap<Integer, Long> powerOfFive = new HashMap<>();

    long acc = 1;
    for (int i = 1; acc < N; i++) {
      acc *= 3L;
      powerOfThree.put(i, acc);
    }

    acc = 1;
    for (int i = 1; acc < N; i++) {
      acc *= 5L;
      powerOfFive.put(i, acc);
    }

    for (Entry<Integer, Long> powerOfThreeEntry : powerOfThree.entrySet()) {
      for (Entry<Integer, Long> powerOfFiveEntry : powerOfFive.entrySet()) {
        if (powerOfThreeEntry.getValue()+powerOfFiveEntry.getValue() == N){
          StringBuilder ans = new StringBuilder().append(powerOfThreeEntry.getKey()).append(" ").append(powerOfFiveEntry.getKey());
          out.println(ans);
          return;
        }
      }
    }
    out.println(-1);

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
