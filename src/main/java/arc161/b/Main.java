package arc161.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    int T = sc.nextInt();

    long max = (long) Math.pow(10, 18);
    // long max = (long) Math.pow(10, 5);
    String binaryRepresentation = Long.toBinaryString(max);

    LOGGER.info(binaryRepresentation);
    LOGGER.info("" + binaryRepresentation.length());

    ArrayList<String> combinations = new ArrayList<>();
    for (int i = 0; i < binaryRepresentation.length()-2; i++) {
      for (int j = i+1; j < binaryRepresentation.length()-1; j++) {
        for (int k = j+1; k < binaryRepresentation.length(); k++) {
          char[] letters = new char[binaryRepresentation.length()];
          Arrays.fill(letters, '0');
          letters[i] = '1';
          letters[j] = '1';
          letters[k] = '1';
          combinations.add(new String(letters));
          // LOGGER.info(new String(letters));
        }
      }
    }

    Collections.sort(combinations);
    ArrayList<Long> inputs = new ArrayList<>();
    LinkedHashMap <Long, Long> table = new LinkedHashMap<>();
    for (int i = 0; i < T; i++) {
      Long N = sc.nextLong();
      inputs.add(N);
      table.put(N, (long) -1);
    }
    Collections.sort(inputs);

    int checkedPointer = 0;
    for (int i = 0; i < combinations.size(); i++) {
      Long target = Long.parseLong(combinations.get(i), 2);
      if (target < inputs.get(checkedPointer)){
        table.replace(inputs.get(i), target);
      }else {
        checkedPointer++;
      }
    }

    table.forEach((k,v) -> out.println(v));
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
