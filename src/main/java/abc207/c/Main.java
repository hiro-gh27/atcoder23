package abc207.c;

import java.io.*;
import java.util.ArrayList;
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
    int numberOfRange = sc.nextInt();

    ArrayList<Range> ranges = new ArrayList<>();
    for (int i = 0; i < numberOfRange; i++) {
      int t = sc.nextInt();
      int left = sc.nextInt();
      int right = sc.nextInt();

      Range range = null;
      switch (t){
        case 1:
          range = new Range(left, right, true, true);
          break;
        case 2:
          range = new Range(left, right, true, false);
          break;
        case 3:
          range = new Range(left, right, false, true);
          break;
        case 4:
          range = new Range(left, right, false, false);
          break;
      }
      ranges.add(range);
    }

    int count = 0;
    for (int i = 0; i < ranges.size(); i++) {
      for (int j = i+1; j < ranges.size(); j++) {
        Range ri = ranges.get(i);
        Range rj = ranges.get(j);
        if (ri.overlapsWith(rj)){
          count++;
        }
      }
    }

    out.println(count);
  }

  static class Range {
    int start;
    int end;
    boolean startInclusive;
    boolean endInclusive;

    public Range(int start, int end, boolean startInclusive, boolean endInclusive) {
      this.start = start;
      this.end = end;
      this.startInclusive = startInclusive;
      this.endInclusive = endInclusive;
    }

    public boolean overlapsWith(Range other){
      if (this.end < other.start || other.end < this.start){
        return false;
      }
      if (this.end == other.start && !(this.endInclusive && other.startInclusive)){
        return false;
      }
      if (other.end == this.start && !(other.endInclusive && this.startInclusive)){
        return false;
      }

      return true;
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
