package agc014.a;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
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

  class CookieExchange{
    ArrayList<Integer> cookieList;

    HashSet<String> already = new HashSet<>();

    public CookieExchange(ArrayList<Integer> cookieList) {
      this.cookieList = cookieList;
    }

    boolean exchange() throws Exception{
      int a = cookieList.get(1)/2 + cookieList.get(2)/2;
      int b = cookieList.get(0)/2 + cookieList.get(2)/2;
      int c = cookieList.get(0)/2 + cookieList.get(1)/2;

      String key = a + " " + b + " " + c;
      if (already.contains(key)) throw new Exception();
      already.add(key);

      if (a % 2 == 1 || b % 2 == 1 || c % 2 == 1) return true;

      cookieList = new ArrayList<>();
      cookieList.add(a);
      cookieList.add(b);
      cookieList.add(c);

      return false;
    }

  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    ArrayList<Integer> cookies = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      cookies.add(sc.nextInt());
    }

    boolean isAllEven = true;
    for (Integer cookie : cookies) {
      if (cookie % 2 == 1){
        isAllEven = false;
        break;
      }
    }
    if (!isAllEven){
      out.println(0);
      return;
    }

    CookieExchange ce = new CookieExchange(cookies);

    int count = 1;
    try {
      while (!ce.exchange()){
        count++;
      }
    } catch (Exception e){
      count = -1;
    }
    out.println(count);
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
