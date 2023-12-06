package abc214.c;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
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

  class Event implements Comparable<Event>{
    int id;
    int S;

    int T;

    public Event(int id, int s, int t) {
      this.id = id;
      S = s;
      T = t;
    }

    @Override
    public int compareTo(Event o) {
      if (this.T == o.T) {
        return this.S - o.S;
      }else {
        return this.T - o.T;
      }
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();

    TreeMap<Integer, Integer> visited = new TreeMap<>();

    int[] arrS = new int[N];
    int[] arrT = new int[N];
    for (int i = 0; i < N; i++) {
      arrS[i] = sc.nextInt();
    }
    for (int i = 0; i < N; i++) {
      arrT[i] = sc.nextInt();
    }

    PriorityQueue<Event> eventsQueue = new PriorityQueue<>();
    HashMap<Integer, Integer> actionMap = new HashMap<>();

    for (int i = 0; i < N; i++) {
      eventsQueue.add(new Event(i, arrS[i], arrT[i]));
      actionMap.put(i, arrS[i]);
    }

    while (!eventsQueue.isEmpty()) {
      Event e = eventsQueue.poll();

      int currentId = e.id;
      int currentTime = e.T;

      if (visited.containsKey(currentId)){
        int minimum = Math.min(visited.get(currentId), currentTime);
        visited.put(currentId, minimum);
      }else {
        visited.put(currentId, currentTime);
      }

      if (visited.size() == N) break;
      int nextId = (e.id+1) % N;
      eventsQueue.add(new Event(nextId, actionMap.get(nextId), e.T+e.S));
    }

    visited.values().forEach(out::println);

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
