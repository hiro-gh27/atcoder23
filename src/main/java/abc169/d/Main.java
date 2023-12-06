package abc169.d;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.logging.*;
import javax.swing.plaf.IconUIResource;

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

  class DivideFinder {
    HashSet<Long> history = new HashSet<>();

    long findMinimum(long target){
      ArrayList<Long> found = new ArrayList<>();
      for (long i = 1; i*i < target; i++) {
        if (target%i == 0){
          found.add(i);
          found.add(target/i);
        }
      }

      Collections.sort(found);
      for (Long divider : found) {
        if (divider == 1) continue;
        if (history.contains(divider)) continue;
        history.add(divider);
        return divider;
      }

      // 1で割り切れる約数が見つかるので呼ばれない
      return target;
    }

    ArrayList<Long> atomicList(long target){
      ArrayList<Long> found = new ArrayList<>();
      long rest = target;
      for (long i = 2; i*i < target; i++) {
        while (rest%i==0){
          found.add(i);
          rest /= i;
        }
      }
      if (rest>1) found.add(rest);
      return found;
    }

  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    long N = sc.nextLong();

    DivideFinder finder = new DivideFinder();

    var atomList = finder.atomicList(N);
    var map = new HashMap<Long, Long>();
    for (Long atom : atomList) {
      map.compute(atom, (k,v)-> v == null?1:v+1);
    }

    long ans = 0;
    for (Entry<Long, Long> kv : map.entrySet()) {
      long value = kv.getValue();
      for (long i = 1; i < value+1; i++) {
        ans++;
        if (value-i > i){
          value -= i;
        }else {
          break;
        }
      }
    }

    int count = 0;
    while (N>1){
      long divider = finder.findMinimum(N);
      N = N / divider;
      count++;
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
