package abc257.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
  class Person implements Comparable<Person> {
    int height;
    int isAdult;

    public Person(int height, int isAdult) {
      this.height = height;
      this.isAdult = isAdult;
    }

    @Override
    public int compareTo(Person o) {
      return height - o.height;
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    char[] adultOrChild = sc.next().toCharArray();

    ArrayList<Person> peopleList = new ArrayList<>();
    TreeSet<Integer> heightSet = new TreeSet<>();

    int adultCount = 0;
    for (char letter : adultOrChild) {
      int height = sc.nextInt();
      int group = letter - '0';
      peopleList.add(new Person(height, group));
      heightSet.add(height);
      if (group == 1) adultCount++;
    }
    Collections.sort(peopleList);

    if (adultCount == 0 || adultCount == N){
      out.println(N);
      return;
    }

    int personIndex = 0;
    int judgedCorrectedChildren = 0;
    int ans = -1;
    for (Integer height : heightSet) {
      while (personIndex < peopleList.size()){
        if (peopleList.get(personIndex).height == height){
          break;
        }
        if (peopleList.get(personIndex).isAdult == 0){
          judgedCorrectedChildren++;
        }else {
          adultCount --;
        }
        personIndex++;
      }
      ans = Math.max(ans, judgedCorrectedChildren + adultCount);
    }
    out.println(ans);



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
