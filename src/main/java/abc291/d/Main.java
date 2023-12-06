package abc291.d;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
  
  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int numberOfCards = sc.nextInt();
    int[][] cards = new int[numberOfCards][2];


    for (int i = 0; i < numberOfCards; i++) {
      String[] strCard = sc.nextLine().split(" ");
      cards[i][0] = Integer.parseInt(strCard[0]);
      cards[i][1] = Integer.parseInt(strCard[1]);
    }

    long[][] calArray = new long[2][2];
    calArray[0][0] = 1;
    calArray[0][1] = 1;

    for (int i = 0; i < cards.length-1; i++) {
      if (cards[i][0] != cards[i+1][0]) calArray[1][0]+=calArray[0][0];
      if (cards[i][0] != cards[i+1][1]) calArray[1][1]+=calArray[0][0];

      if (cards[i][1] != cards[i+1][0]) calArray[1][0]+=calArray[0][1];
      if (cards[i][1] != cards[i+1][1]) calArray[1][1]+=calArray[0][1];

      calArray[0][0] = calArray[1][0] % 998244353;
      calArray[0][1] = calArray[1][1] % 998244353;

      calArray[1][0] = 0;
      calArray[1][1] = 0;
    }
    out.println((calArray[0][0]+calArray[0][1])%998244353);

  }


  
  public HashSet<Pair<Integer, Integer>> cardCombination(Card current, Card next){
    HashSet<Pair<Integer, Integer>> pairs = new HashSet<>();
    addPairIfDifferent(pairs, current.getFront(), next.getFront());
    addPairIfDifferent(pairs, current.getFront(), next.getBack());
    addPairIfDifferent(pairs, current.getBack(), next.getFront());
    addPairIfDifferent(pairs, current.getBack(), next.getBack());
    return pairs;
  }

  // 不要??
  public void addPairIfDifferent(HashSet<Pair<Integer, Integer>> pairs, int first, int second){
    if (first != second){
      pairs.add(new Pair<>(first, second));
    }
  }


  private class Pair<T, U>{
    private T first;

    private U second;

    public Pair(T first, U second) {
      this.first = first;
      this.second = second;
    }

    public T getFirst() {
      return first;
    }

    public U getSecond() {
      return second;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair<?, ?> pair = (Pair<?, ?>) o;
      return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }

    @Override
    public String toString() {
      return "Pair{" +
          "first=" + first +
          ", second=" + second +
          '}';
    }

  }



  private Card readCardFromScanner(MyScanner scanner){
    String[] words = scanner.nextLine().split(" ");
    return new Card(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
  }

  public class Card{
    private int front;
    private int back;

    public Card(int front, int back) {
      this.front = front;
      this.back = back;
    }

    public int getFront() {
      return front;
    }

    public void setFront(int front) {
      this.front = front;
    }

    public int getBack() {
      return back;
    }

    public void setBack(int back) {
      this.back = back;
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
