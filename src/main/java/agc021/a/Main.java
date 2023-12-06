package agc021.a;

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
    String N = sc.nextLine();
    char[] numbersChar = N.toCharArray();

    for (int i = numbersChar.length - 1; i >= 1; i--) {
      int upperDigit = numbersChar[i-1] - '0';
      int currentDigit = numbersChar[i] - '0';

      int noModifiedTotal = currentDigit + upperDigit;
      if (upperDigit > 0){
        int modifiedTotal = upperDigit-1 + 9;
        if (modifiedTotal >= noModifiedTotal){
          numbersChar[i-1] = (char) (upperDigit-1 + '0');
          numbersChar[i] = (char) (9 + '0');
        }
      } else {
        // 1つうえの位が0なのでそのさらに上を探索する必要あり?
        for (int j = i-2; j >= 0 ; j--) {
          int upper = numbersChar[j] - '0';
          if (upper > 0) {
            for (int k = j+1; k <= i; k++){
              numbersChar[k] = (char)(9+'0');
            }
            numbersChar[j] = (char) (upper-1 + '0');
            break;
          }
        }
      }
    }

    int total = 0;
    for (char c : numbersChar) {
      int digit = c - '0';
      total += digit;
    }
    out.println(total);

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
