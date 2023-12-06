package arc137.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    int N = sc.nextInt();
    int[] inputs = new int[N];
    for (int i = 0; i < inputs.length; i++) {
      inputs[i] = sc.nextInt();
    }

    int[] acc = new int[N];
    acc[0] = getChangeValue(inputs[0]);
    for (int i = 1; i < acc.length; i++) {
      int changeVolume =
      acc[i] = acc[i-1] + getChangeValue(inputs[i]);
    }
    LOGGER.info(Arrays.toString(acc));

    int diffMax = 0;
    int diffMin = 0;

    int currentMin = 0;
    for (int i = 0; i < acc.length; i++) {
      diffMax = Math.max(diffMax, acc[i]-currentMin);
      currentMin = Math.min(currentMin, acc[i]);
    }

    int currentMax = 0;
    for (int i = 0; i < acc.length; i++) {
      diffMin = Math.min(diffMin, acc[i]-currentMax);
      currentMax = Math.max(currentMax, acc[i]);
    }
    // ならびかえ

    int ans = diffMax + 1 - diffMin;
    out.println(ans);

  }

  public int getChangeValue(int value){
    if (value != 0 && value != 1){
      throw new RuntimeException();
    }
    return value == 0 ? 1 : -1;
  }

  public void updateMax(HashMap<Integer, Integer> zeroOneMax, int key, int value){
    int currentMax = zeroOneMax.getOrDefault(key, 0);
    zeroOneMax.put(key, Math.max(currentMax, value));
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
