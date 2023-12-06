package abc159.d;

import java.io.*;
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

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int n = sc.nextInt();

    long[] inputs = new long[n];
    long[] counts = new long[n+1];
    long[] combinations = new long[n+1];

    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      inputs[i] = a;
      counts[a] ++;
    }

    for (int i = 0; i < n; i++) {
      combinations[i] = calNumberOfCombinations(counts[i]);
    }

    long total = 0;
    for (long combination : combinations) {
      total += combination;
    }

    for (int i = 0; i < inputs.length; i++) {
      int number = (int)inputs[i];
      long ignored = calNumberOfCombinations(counts[number]-1);
      long diff = combinations[number] - ignored;
      out.println(total - diff);
    }
    
  }

  private long calNumberOfCombinations(long elements){
    if (elements >= 2){
      return ( (elements * (elements-1)) / 2);
    }
    return 0;
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
