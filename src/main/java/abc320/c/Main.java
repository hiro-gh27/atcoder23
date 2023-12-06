package abc320.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.stream.Collectors;

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

  private char[][] readReels(MyScanner sc, int baseSize){
    char[][] reels = new char[3][3*baseSize];
    for (int i = 0; i < 3; i++) {
      String input = sc.next();
      reels[i] = input.repeat(3).toCharArray();
    }
    return reels;
  }

  private List<Map<Character, List<Integer>>> buildIndexList(char[][] reels){
    List<Map<Character, List<Integer>>> indexList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      indexList.add(new HashMap<>());
    }

    for (int i = 0; i < reels.length; i++) {
      for (int j = 0; j < reels[i].length; j++) {
        var index = indexList.get(i);
        var cnt = index.getOrDefault(reels[i][j], new ArrayList<>());
        cnt.add(j);
        index.put(reels[i][j], cnt);
      }
    }
    return indexList;
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int M = sc.nextInt();

    char[][] reels = readReels(sc, M);
    List<Map<Character, List<Integer>>> indexList = buildIndexList(reels);


    int ans = Integer.MAX_VALUE;
    // '0'~'9': cnt
    for (int i = 0; i <= 9; i++) {
      char collectKey = (char)(i + '0');

      List<List<Integer>> combinationSource = indexList.stream()
          .filter(index -> index.containsKey(collectKey))
          .map(index -> index.get(collectKey))
          .collect(Collectors.toList());

      if (combinationSource.size() != 3) continue;

      for (Integer x : combinationSource.get(0)) {
        for (Integer y : combinationSource.get(1)) {
          for (Integer z : combinationSource.get(2)) {
            if (includeSameValue(x, y, z)) continue;
            ans = Math.min(ans, maxValueByThree(x,y,z));
          }
        }
      }
    }

    if (ans == Integer.MAX_VALUE){
      ans = -1;
    }
    out.println(ans);
  }

  public int maxValueByThree(int a, int b, int c){
    int max1 = Math.max(a, b);
    int max2 = Math.max(b, c);
    return Math.max(max1, max2);
  }
  public boolean includeSameValue(int a, int b, int c){
    return a == b || b==c || a==c;
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
