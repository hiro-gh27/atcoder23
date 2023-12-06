package abc064.c;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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

  private String getColorJP(int point){
    if (point < 400){
      return "灰色";
    } else if (point < 800) {
      return "茶色";
    } else if (point < 1200) {
      return "緑色";
    } else if (point < 1600) {
      return "水色";
    } else if (point < 2000) {
      return "青色";
    } else if (point < 2400) {
      return "黄色";
    } else if (point < 2800) {
      return "橙色";
    } else if (point < 3200) {
      return "赤色";
    } else {
      return "";
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    HashMap<String, Integer> numberOfColor = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int point = sc.nextInt();
      String colorJP = getColorJP(point);
      if (!numberOfColor.containsKey(colorJP)){
        numberOfColor.put(colorJP, 0);
      }
      int count = numberOfColor.get(colorJP);
      numberOfColor.put(colorJP, count+1);
    }

    int free = 0;
    int ans = 0;
    for (Entry<String, Integer> colorCount : numberOfColor.entrySet()) {
      if ("".equals(colorCount.getKey())){
        free = colorCount.getValue();
      }else {
        ans += 1;
      }
    }

    if (ans == 0){
      int min = 1;
      int max = free;
      out.println(min + " " + max);
    } else {
      int min = ans;
      int max = min + free;
      out.println(min + " " + max);
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
