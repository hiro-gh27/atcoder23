package abc314.d;

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

  class Query {
    int type;
    int index;

    char args;

    public Query(int type, int index, char args) {
      this.type = type;
      this.index = index;
      this.args = args;
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    char[] inputs = sc.next().toCharArray();
    int Q = sc.nextInt();

    ArrayList<Query> queriesList = new ArrayList<>();
    int lastConvertIndex = -1;
    for (int i = 0; i < Q; i++) {
      Query query = new Query(sc.nextInt(), sc.nextInt(), sc.next().toCharArray()[0]);
      queriesList.add(query);
      if (query.type == 2 || query.type == 3){
        lastConvertIndex = i;
      }
    }

    for (int i = 0; i < queriesList.size(); i++) {
      Query currentQuery = queriesList.get(i);
      if (currentQuery.type == 1){
        inputs[currentQuery.index-1] = currentQuery.args;
      }
      if (lastConvertIndex == i){
        String s = new String(inputs);
        if (currentQuery.type == 2){
          s = s.toLowerCase();
        }else {
          s = s.toUpperCase();
        }
        inputs = s.toCharArray();
      }
    }

    out.println(new String(inputs));

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
