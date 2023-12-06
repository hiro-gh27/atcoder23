package abc243.c;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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

    HashMap<Integer, Integer> rightmostPositionForLeftWalkers = new HashMap<>();
    HashMap<Integer, Integer> leftmostPositionForRightWalkers = new HashMap<>();

    int numberOfPeople = sc.nextInt();

    List<Point> positions = new ArrayList<>();
    for (int i = 0; i < numberOfPeople; i++) {
      positions.add(new Point(sc.nextInt(), sc.nextInt()));
    }

    // R or L
    char[] directions = sc.next().toCharArray();

    for (int i = 0; i < numberOfPeople; i++) {
      Point currentPoint = positions.get(i);
      if (directions[i] == 'R'){
        updateLeftmostPositionForRightWalkers(currentPoint, leftmostPositionForRightWalkers);
      }else {
        updateRightmostPositionForLeftWalkers(currentPoint, rightmostPositionForLeftWalkers);
      }
    }

    for (Integer Y : leftmostPositionForRightWalkers.keySet()) {
      Integer minX = leftmostPositionForRightWalkers.get(Y);
      Integer maxX = rightmostPositionForLeftWalkers.get(Y);
      if (minX != null & maxX != null) {
        if (minX < maxX){
          out.println("Yes");
          return;
        }
      }
    }
    out.println("No");
  }

  private void updateRightmostPositionForLeftWalkers(Point currentPoint, HashMap<Integer, Integer> rightmostPositionForLeftWalkers){
    Integer maxX = rightmostPositionForLeftWalkers.get(currentPoint.y);
    if (Objects.nonNull(maxX)){
      if (currentPoint.x > maxX){
        maxX = currentPoint.x;
      }
      rightmostPositionForLeftWalkers.put(currentPoint.y, maxX);
    } else {
      rightmostPositionForLeftWalkers.put(currentPoint.y, currentPoint.x);
    }
  }

  private void updateLeftmostPositionForRightWalkers(Point currentPoint, HashMap<Integer, Integer> leftmostPositionForRightWalkers){
    Integer minX = leftmostPositionForRightWalkers.get(currentPoint.y);
    if (Objects.nonNull(minX)){
      if (currentPoint.x < minX){
        minX = currentPoint.x;
      }
      leftmostPositionForRightWalkers.put(currentPoint.y, minX);
    }else{
      leftmostPositionForRightWalkers.put(currentPoint.y, currentPoint.x);
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
