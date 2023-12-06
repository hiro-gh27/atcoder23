package abc260.c;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    int N = sc.nextInt();     // N
    int X = sc.nextInt(); // X
    int Y = sc.nextInt(); // Y

    long[] red = new long[N+1];
    long[] blue = new long[N+1];

    red[N] = 1;
    for (int i = N; i > 1; i--) {
      // split red
      red[i-1] += red[i];
      blue[i] += red[i] * X;

      // split blue
      red[i-1] += blue[i];
      blue[i-1] += blue[i] * Y;
    }

    out.println(blue[1]);

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
