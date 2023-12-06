package abc245.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
    int sequenceLength = sc.nextInt();
    int maxDifference = sc.nextInt();

    boolean[][] canArrive = new boolean[2][sequenceLength];
    canArrive[0][0] = true;
    canArrive[1][0] = true;

    List<Integer> sequenceA = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    List<Integer> sequenceB = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

    for (int i = 1; i < canArrive[0].length; i++) {
      int currentA = sequenceA.get(i);
      int currentB = sequenceB.get(i);
      if (canArrive[0][i-1]){
        int beforeA = sequenceA.get(i-1);
        if (Math.abs(beforeA-currentA)<=maxDifference){
          canArrive[0][i] = true;
        }
        if (Math.abs(beforeA-currentB)<=maxDifference){
          canArrive[1][i] = true;
        }
      }

      if (canArrive[1][i-1]){
        int beforeB = sequenceB.get(i-1);
        if (Math.abs(beforeB-currentA)<=maxDifference){
          canArrive[0][i] = true;
        }

        if (Math.abs(beforeB-currentB)<=maxDifference){
          canArrive[1][i] = true;
        }

      }

      if (!canArrive[0][i] && !canArrive[1][i]){
        out.println("No");
        return;
      }

    }
    out.println("Yes");


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
