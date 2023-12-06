package abc330.e;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
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
    int Q = sc.nextInt();

    int[] A = new int[N];
    TreeMap<Integer, Integer> countOfNumberMap = new TreeMap<>();
    for (int i = 0; i < N; i++) {
      A[i] = sc.nextInt();
      countOfNumberMap.put(A[i], countOfNumberMap.getOrDefault(A[i],0)+1);
    }
    
    for (int i = 0; i < Q; i++) {
      int index = sc.nextInt() - 1;
      int newValue = sc.nextInt();
      int oldValue = A[index];

      // 入れ替え作業
      countOfNumberMap.put(oldValue, countOfNumberMap.getOrDefault(oldValue, 0)-1);
      countOfNumberMap.put(newValue, countOfNumberMap.getOrDefault(newValue, 0)+1);
      if (countOfNumberMap.get(oldValue) <= 0) {
        countOfNumberMap.remove(oldValue);
      }
      A[index] = newValue;

      // 判定処理
      if (countOfNumberMap.firstKey() > 0){
        out.println(0);
        continue; // next
      }
      // 怪しい
      if (countOfNumberMap.keySet().size() - 1 == countOfNumberMap.lastKey()){
        out.println(countOfNumberMap.lastKey()+1);
      }
      var set = countOfNumberMap.entrySet();

    }
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
