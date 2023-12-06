package abc323.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
    int M = sc.nextInt();
    TreeMap<Integer, ArrayList<Integer>> scoreMap = new TreeMap<>(Comparator.reverseOrder());
    int[] values = new int[M];
    for (int i = 0; i < M; i++) {
      int value = sc.nextInt();
      ArrayList<Integer> list = scoreMap.getOrDefault(value, new ArrayList<>());
      list.add(i);
      scoreMap.put(value, list);
      values[i] = value;
    }

    char[][] players = new char[N][M];
    ArrayList<Integer> currentScores = new ArrayList<>();
    int maxPoint = 0;
    int winner = 0;
    for (int i = 0; i < N; i++) {
      int point = i+1;
      players[i] = sc.next().toCharArray();
      for (int j = 0; j < players[i].length; j++) {
        if (players[i][j] =='o') point+=values[j];
      }
      if (point > maxPoint){
        maxPoint = point;
        winner = i;
      }
      currentScores.add(point);
    }

    for (int i = 0; i < currentScores.size(); i++) {
      int needs = 0;
      int current = currentScores.get(i);
      for (Entry<Integer, ArrayList<Integer>> entry : scoreMap.entrySet()) {
        if (i == winner) break;
        for (Integer index : entry.getValue()) {
          if (current>maxPoint) break;
          if (players[i][index] == 'x'){
            current += entry.getKey(); // bonus
            needs++;
          }
        }
        
      }
      out.println(needs);
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
