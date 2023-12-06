package abc298.c;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

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

  class QueryHistory {

    HashMap<Integer, ArrayList<Integer>> box;

    HashMap<Integer, TreeSet<Integer>> indexForCards;

    public QueryHistory() {
      box = new HashMap<>();
      indexForCards = new HashMap<>();
    }

    void addCard(int cardNumber, int boxNumber){
      // boxにカード追加
      if (!box.containsKey(boxNumber)) box.put(boxNumber, new ArrayList<>());
      box.get(boxNumber).add(cardNumber);

      // カードがどのボックスに入っているか
      if (!indexForCards.containsKey(cardNumber)) indexForCards.put(cardNumber, new TreeSet<>());
      indexForCards.get(cardNumber).add(boxNumber);
    }

    ArrayList<Integer> getCardInBox(int boxNumber){
      Collections.sort(box.get(boxNumber));
      return box.get(boxNumber);
    }

    TreeSet<Integer> getIndexForCards(int cardNumber){
      return indexForCards.get(cardNumber);
    }

  }


  private void print(Collection<Integer> collection){
    StringBuilder sb = new StringBuilder();
    for (Integer integer : collection) {
      sb.append(integer).append(" ");
    }
    out.println(sb.toString());
  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int Q = sc.nextInt();

    QueryHistory cardBox = new QueryHistory();
    for (int i = 0; i < Q; i++) {
      int queryType = sc.nextInt();
      int qi = sc.nextInt();

      if (queryType == 1){
        int qj = sc.nextInt();
        cardBox.addCard(qi, qj);
      } else if (queryType == 2) {
        print(cardBox.getCardInBox(qi));
      } else if (queryType == 3) {
        print(cardBox.getIndexForCards(qi));
      }
    }



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
