package agc029.a;

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

  int operationCost = 0;
  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    char[] blackWhiteStones = sc.next().toCharArray();
    int whiteCount = 0;
    long cost = 0;
    for (int i = 0; i < blackWhiteStones.length; i++) {
      if (blackWhiteStones[i] == 'W'){
        cost += (i - whiteCount);
        whiteCount++;
      }
    }
    out.println(cost);
  }


  public void swapStones(char[] stones, int a, int b){
    char temp = stones[a];
    stones[a] = stones[b];
    stones[b] = temp;
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
