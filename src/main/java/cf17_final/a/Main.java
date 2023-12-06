package cf17_final.a;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    String line = sc.next();
    char[] inputs = line.toCharArray();
    int inputsLength = inputs.length;
    char[] akihabara = "AKIHABARA".toCharArray();

    ArrayDeque<String> deque = new ArrayDeque<>();
    deque.add(line);

    for (int i = inputsLength; i >= 0; i--) {
      ArrayList<String> tmp = new ArrayList<>();
      while (!deque.isEmpty()){
        String target = deque.poll();
        ArrayList<Character> letters = toCharList(target);

        String out1 = convertString(letters);

        letters.add(i, 'A');
        String out2 = convertString(letters);

        if (out1.length() <= akihabara.length){
          tmp.add(out1);
        }

        if (out2.length() <= akihabara.length){
          tmp.add(out2);
        }

      }
      deque.addAll(tmp);
    }

    while (!deque.isEmpty()){
      String check = deque.poll();
      if ("AKIHABARA".equals(check)){
        out.println("YES");
        return;
      }
    }
    out.println("NO");
  }

  public ArrayList<Character> toCharList(String input){
    ArrayList<Character> letters = new ArrayList<>();
    for (char c : input.toCharArray()) {
      letters.add(c);
    }
    return letters;
  }

  public String convertString(ArrayList<Character> letters){
    return letters.stream().map(String::valueOf).collect(Collectors.joining());
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
