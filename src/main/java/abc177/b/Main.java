package abc177.b;

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

    char[] sLetters = sc.nextLine().toCharArray();
    char[] tLetters = sc.nextLine().toCharArray();

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i <= sLetters.length-tLetters.length; i++) {
      int toReplace = 0;
      for (int j = 0; j < tLetters.length; j++) {
        if (sLetters[i+j] != tLetters[j]){
          toReplace++;
        }
      }

      if (toReplace < ans){
        ans = toReplace;
      }
    }
    
    out.println(ans);

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
