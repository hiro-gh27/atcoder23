package arc156.a;

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

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    for (int i = 0; i < N; i++) {
      int s = sc.nextInt();
      char[] letters = sc.next().toCharArray();
      int[] numbers = convertNumbers(letters);
      out.println(resolve(numbers));
    }
  }

  private int[] convertNumbers(char[] letters){
    int[] numbers = new int[letters.length];
    for (int i = 0; i < letters.length; i++) {
      numbers[i] = letters[i] - '0';
    }
    return numbers;
  }

  private int resolve(int[] numbers){

    int oneCount = 0;

    for (int number : numbers) {
      if (number == 1) oneCount ++;
    }

    if (oneCount == 0) return 0;
    if (oneCount % 2 != 0) return -1;
    if (oneCount >= 4) return oneCount/2;

    // 2個の場合
    ArrayList<Integer> index = new ArrayList<>();
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == 1) index.add(i);
    }

    int diff = index.get(1) - index.get(0);

    if (numbers.length <= 3){
      if (diff == 1) return -1;
      else return 1;
    }

    if (numbers.length == 4){
      if (index.get(0) == 1 && index.get(1) == 2){
        return -1;
      }
    }

    if (diff == 1){
      return 2;
    }else {
      return 1;
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
