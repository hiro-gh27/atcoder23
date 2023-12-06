package zone2021.d;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.logging.*;
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
    char[] chiperText = sc.nextLine().toCharArray();
    LinkedList<Character> decryptedText = new LinkedList<>();

    boolean isReverseOrder = false;
    for (int i = 0; i < chiperText.length; i++) {
      char currentCharacter = chiperText[i];
      if (currentCharacter == 'R'){
        isReverseOrder = !isReverseOrder;
      } else {
        if (!decryptedText.isEmpty()){
          char targetChar;
          if (isReverseOrder){
            targetChar = decryptedText.peekLast();
            if (targetChar == currentCharacter) {
              decryptedText.pollLast();
            } else {
              decryptedText.addLast(currentCharacter);
            }
          }else {
            targetChar = decryptedText.peekFirst();
            if (targetChar == currentCharacter) {
              decryptedText.pollFirst();
            }else {
              decryptedText.addFirst(currentCharacter);
            }
          }
        }else {
          decryptedText.add(currentCharacter);
        }
      }
    }

    String ans = "";
    if (isReverseOrder){
      ans = decryptedText.stream().map(Objects::toString).collect(Collectors.joining());
    }else {
      Collections.reverse(decryptedText);
      ans = decryptedText.stream().map(Objects::toString).collect(Collectors.joining());
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
