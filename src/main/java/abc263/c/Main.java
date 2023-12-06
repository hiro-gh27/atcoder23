package abc263.c;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

  class Resolver{
    ArrayList<ArrayList<String>> result = new ArrayList<>();
    String[] letters;

    int M;


    public Resolver(String[] letters, int m) {
      this.letters = letters;
      M = m;
    }

    private void dps(ArrayList<String> acc, int index){

      if (acc.size() == M) {
        result.add(acc);
        return;
      }

      if (index >= letters.length){
        return;
      }

      ArrayList<String> c1 = new ArrayList<>(acc);
      ArrayList<String> c2 = new ArrayList<>(acc);
      c1.add(letters[index]);

      dps(c1, index+1);
      dps(c2, index+1);
    }

  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();

    int N = sc.nextInt();
    int M = sc.nextInt();
    
    String[] letters = new String[M];
    for (int i = 0; i < M; i++) {
      letters[i] = String.valueOf(i+1);
    }

    Resolver resolver = new Resolver(letters, N);
    resolver.dps(new ArrayList<>(), 0);

    for (ArrayList<String> line : resolver.result) {
      out.println(String.join(" ", line));
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
