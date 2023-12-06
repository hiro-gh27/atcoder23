package abc258.b;

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
    int[][] table = new int[N*3][N*3];

    for (int i = 0; i < N; i++) {
      int[] numbers = toNumbers(sc.nextLine().toCharArray());
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < numbers.length; l++) {
            table[j*N+i][k*N+l] = numbers[l];
          }
        }
      }
    }


    int max = 0;
    int numberOfVector = 8;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 原点
        int x = i + N;
        int y = j + N;

        ArrayList<StringBuilder> sbList = new ArrayList<>();
        for (int k = 0; k < numberOfVector; k++) {
          sbList.add(new StringBuilder());
        }

        for (int k = 0; k < N; k++) {
          sbList.get(0).append(String.valueOf(table[x+k][y+k]));
          sbList.get(1).append(String.valueOf(table[x-k][y-k]));
          sbList.get(2).append(String.valueOf(table[x+k][y-k]));
          sbList.get(3).append(String.valueOf(table[x-k][y+k]));
          sbList.get(4).append(String.valueOf(table[x+k][y]));
          sbList.get(5).append(String.valueOf(table[x][y+k]));
          sbList.get(6).append(String.valueOf(table[x-k][y]));
          sbList.get(7).append(String.valueOf(table[x][y-k]));
        }

        for (StringBuilder sb : sbList) {
          max = Math.max(max, Integer.parseInt(sb.toString()));
        }
      }
    }
    out.println(max);
  }

  private int returnReverseMax(StringBuilder sb1, StringBuilder sb2){
    String str1 = sb1.toString();
    String str2 = sb2.toString();
    return Math.max(Integer.parseInt(str1), Integer.parseInt(str2));
  }


  private int[] toNumbers(char[] letters){
    int[] numbers = new int[letters.length];
    for (int i = 0; i < letters.length; i++) {
      numbers[i] = letters[i] - '0';
    }
    return numbers;
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
