package abc314.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    ArrayList<HashSet<Integer>> userBets = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      int C = sc.nextInt();
      HashSet<Integer> bets = new HashSet<>();
      for (int j = 0; j < C; j++) {
        bets.add(sc.nextInt());
      }
      userBets.add(bets);
    }

    int winNumber = sc.nextInt();

    int minimumBetsSize = Integer.MAX_VALUE;
    for (HashSet<Integer> bets : userBets) {
      if (bets.contains(winNumber)){
        minimumBetsSize = Math.min(minimumBetsSize, bets.size());
      }
    }

    ArrayList<Integer> winnersList = new ArrayList<>();
    for (int i = 0; i < userBets.size(); i++) {
      HashSet<Integer> bets = userBets.get(i);
      if (bets.contains(winNumber) && bets.size() == minimumBetsSize){
        winnersList.add(i+1);
      }
    }

    out.println(winnersList.size());
    out.println(winnersList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    
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
