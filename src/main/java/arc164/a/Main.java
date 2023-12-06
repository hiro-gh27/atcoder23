package arc164.a;

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
    int T = sc.nextInt();

    for (int i = 0; i < T; i++) {
      long testCaseT = sc.nextLong();
      long testCaseK = sc.nextLong();
      String result = resolve(testCaseT, testCaseK);
      out.println(result);
    }
  }

  class MaxPower3{
    ArrayList<Long> powerOfThree = new ArrayList<>();

    public MaxPower3() {
      long result = 1;
      powerOfThree.add(result);

      while (result < (Long.MAX_VALUE/3)){
        result = result * 3;
        powerOfThree.add(result);
      }
    }

    public long getMaxValueUnderTheLimit(long limit){
      long result = 0;
      for (Long power : powerOfThree) {
        if (power <= limit) result = power;
        else break;
      }
      return result;
    }
  }

  private String resolve(long T, long K){
    long minimum = 0;
    MaxPower3 power3 = new MaxPower3();
    long rest = T;
    while (rest > 0){
      minimum++;
      rest -= power3.getMaxValueUnderTheLimit(rest);
    }

    if (K < minimum){
      return "No";
    }

    if ((K - minimum) % 2 == 0){
      return "Yes";
    }else {
      return "No";
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
