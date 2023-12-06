package abc321.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

  private long cal(ArrayList<Long> numbers, long target){
    ArrayList<Long> newList = new ArrayList<>(numbers);
    newList.add(target);

    long total = 0;
    for (Long aLong : newList) {
      total += aLong;
    }
    Collections.sort(newList);
    return total - newList.get(0) - newList.get(newList.size()-1);
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();

    int N = sc.nextInt();
    int X = sc.nextInt();
    ArrayList<Long> numbers = new ArrayList<>();

    for (int i = 0; i < N - 1; i++) {
      numbers.add(sc.nextLong());
    }

    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = 0; i <= 100; i++) {
      long value = cal(numbers, i);
      if (value >= X){
        ans.add(i);
      }
    }

    if (ans.isEmpty()){
      out.println(-1);
      return;
    }

    int a = Integer.MAX_VALUE;
    for (Integer an : ans) {
      a = Math.min(a, an);
    }
    out.println(a);









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
