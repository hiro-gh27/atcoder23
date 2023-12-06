package abc143.d;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

  class Tree {

    Integer[] list;

    public Tree(Integer[] list){
      this.list = list;
    }

    int lowerIndex(int low, int high, int searchValue){
      int diff = high - low;
      if (diff == 1){
        return low; // OR to;
      }

      int mid = low + (high- low)/2;

      if (list[mid] >= searchValue){
        return lowerIndex(low, mid, searchValue);
      } else {
        return lowerIndex(mid, high, searchValue);
      }

    }
  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    ArrayList<Integer> uniqueNumbers = new ArrayList<>();
    int N = sc.nextInt();
    for (int i = 0; i < N; i++) {
      uniqueNumbers.add(sc.nextInt());
    }

    Collections.sort(uniqueNumbers);
    Integer[] numbers = uniqueNumbers.toArray(Integer[]::new);

    Tree t = new Tree(numbers);

    int ans = 0;
    for (int b = 0; b < numbers.length; b++) {
      for (int a = 0; a < b; a++) {
        if (numbers[a]>=numbers[b]) break;
        int ab = numbers[a] + numbers[b];
        int lower = t.lowerIndex(0, uniqueNumbers.size()-1, ab);
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