package abc315.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
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

    HashMap<Integer, ArrayList<Integer>> iceCreamMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int flavor = sc.nextInt();
      int tastePoint = sc.nextInt();
      var set = iceCreamMap.getOrDefault(flavor, new ArrayList<>());
      set.add(tastePoint);
      iceCreamMap.put(flavor, set);
    }

    iceCreamMap.forEach((k, v) -> v.sort(Collections.reverseOrder()));

    // 同じ味で最大値を探す
    int maxSameTaste = 0;
    for (Entry<Integer, ArrayList<Integer>> sameTaste : iceCreamMap.entrySet()) {
      if (sameTaste.getValue().size()<2){
        continue;
      }
      int count = 0;
      int maxCombination = 0;
      for (Integer tastePoint : sameTaste.getValue()) {
        maxCombination += (count == 0 ? tastePoint : tastePoint/2);
        count++;
        if (count >= 2) break;
      }
      maxSameTaste = Math.max(maxSameTaste, maxCombination);
    }

    // 異なる味で最大値を探す
    ArrayList<Integer> differentIceCreams = new ArrayList<>();
    for (Entry<Integer, ArrayList<Integer>> tasteSet : iceCreamMap.entrySet()) {
      differentIceCreams.add(tasteSet.getValue().get(0));
    }
    differentIceCreams.sort(Collections.reverseOrder());
    int maxDifferent = 0;
    if (differentIceCreams.size()>1){
      maxDifferent = differentIceCreams.get(0) + differentIceCreams.get(1);
    }

    out.println(Math.max(maxSameTaste, maxDifferent));

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
