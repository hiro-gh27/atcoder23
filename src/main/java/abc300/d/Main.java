package abc300.d;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
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

  int binarySearch(int[] array, int low, int high, double value){
    while (high-low>1){
      int mid = (low+high)/2;
      if (array[mid] <= value){
         low = mid;
      } else {
        high = mid;
      }
    }
    return low;
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    long N = sc.nextLong();

    HashSet<Long> dividable = new HashSet<>();
    ArrayList<Long> primeList = new ArrayList<>();

    // ここまではすんなりたどり着くべき
    for (long i = 2; i < Math.sqrt(N); i++) {
      if (dividable.contains(i)) continue;;
      primeList.add(i);
      for (long j = i; j < Math.sqrt(N); j+=i) {
        dividable.add(j);
      }
    }
    int[] primes = primeList.stream().mapToInt(Long::intValue).toArray();

    int cnt = 0;
    for (int i = 0; i < primes.length; i++) {
      int a = primes[i];
      for (int j = i+1; j < primes.length; j++) {
        int b = primes[j];
        double inner = N / (a * a * b);
        double threshold = Math.sqrt(inner);
        int lowerCaseIndex = binarySearch(primes, 0, primes.length, threshold);
        if (lowerCaseIndex > j){
          int count = lowerCaseIndex - j;
          cnt += count;
        }else {
          break;
        }
      }
    }
    out.println(cnt);
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
