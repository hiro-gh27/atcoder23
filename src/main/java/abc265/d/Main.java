package abc265.d;

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

  class Range{
    int from;
    int to;
    public Range(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      return "Range{" +
          "from=" + from +
          ", to=" + to +
          '}';
    }
  }

  class BinarySearch {
    long[] acc;

    public BinarySearch(long[] acc) {
      this.acc = acc;
    }

    public long matchCase(long target, int from, int to){
      //
      long initLeftmostValue = acc[from];
      while (from<to){
        int mid = (from+to)/2;
        long rangeValue = acc[mid] - initLeftmostValue;
        if (rangeValue<target){
          from = mid+1;
        }else {
          to = mid-1;
        }
      }
      return -1;
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    long P = sc.nextLong();
    long Q = sc.nextLong();;
    long R = sc.nextLong();
    long PQR = P + Q + R;

    long[] A = new long[N];
    long[] acc = new long[N+1];

    for (int i = 0; i < N; i++) {
      A[i] = sc.nextLong();
      acc[i+1] = acc[i]+A[i];
    }

    ArrayList<Range> rangeList = new ArrayList<>();
    for (int i = 1; i < acc.length; i++) {
      for (int j = 0; j < i; j++) {
        if (acc[i] - acc[j] == PQR){
          rangeList.add(new Range(j, i-1));
        }
      }
    }

    BinarySearch bs = new BinarySearch(acc);
    for (Range r : rangeList) {
      bs.matchCase(P, r.from, r.to);
    }

    LOGGER.info(rangeList.toString());

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
