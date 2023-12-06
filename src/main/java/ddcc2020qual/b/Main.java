package ddcc2020qual.b;

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
    int numberOfSection = sc.nextInt();

    int leftIndex = 0;
    int rightIndex = numberOfSection - 1;

    int[] sectionWeight = new int[numberOfSection];
    for (int i = 0; i < numberOfSection; i++) {
      sectionWeight[i] = sc.nextInt();
    }


    int leftSum = sumUpToIndexInclusive(sectionWeight, leftIndex);
    int rightSum = sumUpFromIndexInclusive(sectionWeight, rightIndex);

    while ((rightIndex-leftIndex) > 1){
      int leftSumExpaded = leftSum + sectionWeight[leftIndex + 1];
      int rightSumExpanded = rightSum + sectionWeight[rightIndex - 1];

      int leftDiff = Math.abs(leftSumExpaded - rightSum);
      int rightDiff = Math.abs(leftSum - rightSumExpanded);

      if (leftDiff < rightDiff){
        leftSum = leftSumExpaded;
        leftIndex++;
      }else {
        rightSum = rightSumExpanded;
        rightIndex--;
      }
    }
    
    out.println(Math.abs(leftSum-rightSum));

  }

  private int sumUpToIndexInclusive(int[] values, int endIndex){
    int total = 0;
    for (int i = 0; i <= endIndex; i++) {
      total += values[i];
    }
    return total;
  }

  private int sumUpFromIndexInclusive(int[] values, int startIndex){
    int total = 0;
    for (int i = startIndex; i < values.length; i++) {
      total += values[i];
    }
    return total;
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
