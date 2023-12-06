package abc331.e;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

  class Cuisiue implements Comparable<Cuisiue> {
    int id;
    int price;

    public Cuisiue(int id, int price) {
      this.id = id;
      this.price = price;
    }

    @Override
    public int compareTo(Cuisiue o) {
      if (this.price == o.price) {
        return this.id - o.id;
      }
      return this.price - o.price;
    }
  }

  class CombinationManager {

    int count = 0;
    HashMap<Integer, HashSet<Integer>> batCombinations = new HashMap<>();

    public void add(int mainDishId, int sideDishId) {
      count++;
      HashSet<Integer> sideDishSet = batCombinations.getOrDefault(mainDishId, new HashSet<>());
      sideDishSet.add(sideDishId);
      batCombinations.put(mainDishId, sideDishSet);
    }

    public boolean contains(int mainDishId, int sideDishId) {
      if (batCombinations.containsKey(mainDishId)){
        return batCombinations.get(mainDishId).contains(sideDishId);
      } else {
        return false;
      }
    }

    public void remove(int mainDishId, int sideDishId) {
      count--;
      HashSet<Integer> sides = batCombinations.get(mainDishId);
      sides.remove(sideDishId);
      if (sides.size()>1){
        batCombinations.put(mainDishId, sides);
      } else {
        batCombinations.remove(mainDishId);
      }
    }


  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();

    int N = sc.nextInt();
    int M = sc.nextInt();
    int L = sc.nextInt();

    Cuisiue[] mainDish = new Cuisiue[N];
    Cuisiue[] sideDish = new Cuisiue[M];


    for (int i = 0; i < N; i++) {
      Cuisiue cuisiue = new Cuisiue(i+1, sc.nextInt());
      mainDish[i] = cuisiue;
    }
    for (int i = 0; i < M; i++) {
      Cuisiue cuisiue = new Cuisiue(i+1, sc.nextInt());
      sideDish[i] = cuisiue;
    }
    CombinationManager combinationManager = new CombinationManager();
    for (int i = 0; i < L; i++) {
      combinationManager.add(sc.nextInt(), sc.nextInt());
    }
    Arrays.sort(mainDish, Collections.reverseOrder());
    Arrays.sort(sideDish, Collections.reverseOrder());

    TreeSet<Integer> maxPriceSet = new TreeSet<>();
    for (Cuisiue main : mainDish) {
      for (Cuisiue side : sideDish) {
        if (!combinationManager.contains(main.id, side.id)){
          maxPriceSet.add(main.price+side.price);
          break;
        }
      }
    }
    out.println(maxPriceSet.last());
  }

  int lowerIndex(Cuisiue[] cuisiues, int price) {
    int low = -1;
    int high = cuisiues.length-1;
    while ((high-low)>1) {
      int mid = (high + low) / 2;
      if (cuisiues[mid].price < price) {
        low = mid;
      } else {
        high = mid;
      }
    }
    return low;
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
