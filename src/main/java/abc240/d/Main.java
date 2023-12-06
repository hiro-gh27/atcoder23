package abc240.d;

import java.io.*;
import java.util.ArrayList;
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

  class ConsencutiveNumber {
    int id;
    int count;

    public ConsencutiveNumber(int id, int count) {
      this.id = id;
      this.count = count;
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    ArrayList<ConsencutiveNumber> consencutiveNumbersList = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      int a = sc.nextInt();
      if (consencutiveNumbersList.isEmpty()) {
        ConsencutiveNumber next = new ConsencutiveNumber(a, 1);
        consencutiveNumbersList.add(next);
      } else {
        ConsencutiveNumber before = consencutiveNumbersList.get(consencutiveNumbersList.size() - 1);
        ConsencutiveNumber next;
        if (a == before.id) {
          next = new ConsencutiveNumber(a, before.count + 1);
        }else {
          next = new ConsencutiveNumber(a, 1);
        }
        consencutiveNumbersList.add(next);
      }

      ConsencutiveNumber last = consencutiveNumbersList.get(consencutiveNumbersList.size()-1);
      if (last.id == last.count){
        for (int j = 0; j < last.count; j++) {
          consencutiveNumbersList.remove(consencutiveNumbersList.size()-1);
        }
      }

      out.println(consencutiveNumbersList.size());
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
