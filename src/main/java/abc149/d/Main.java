package abc149.d;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

  private static PrintWriter out;

  public static void main(String[] args) {
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
    int K = sc.nextInt();

    int RWinPoint = sc.nextInt();
    int SWinPoint = sc.nextInt();
    int PWinPoint = sc.nextInt();

    int totalPoint = 0;
    char[] opponentHands = sc.nextLine().toCharArray();
    for (int i = 0; i < opponentHands.length; i++) {
      char hand = opponentHands[i];

      int currentPoint = 0;
      if (hand == 'r'){
        currentPoint += PWinPoint;
      }else if (hand == 's'){
        currentPoint += RWinPoint;
      }else if (hand == 'p'){
        currentPoint += SWinPoint;
      }

      if (i - K >= 0){
        if (hand == opponentHands[i-K]){
          currentPoint = 0;
          opponentHands[i] = '.';
        }
      }

      totalPoint += currentPoint;

    }

    out.println(totalPoint);
    
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
