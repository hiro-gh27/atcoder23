package abc173.d;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
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


  private Long[] convertStringsToLong(String[] stringArrary){
    Long[] longArray = new Long[stringArrary.length];
    for (int i = 0; i < stringArrary.length; i++) {
      longArray[i] = parseStringToLong(stringArrary[i]);
    }
    return longArray;
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    long numberOfPlayers = sc.nextInt();

    Long[] playerFriendlinessPoint = convertStringsToLong(sc.nextLine().split(" "));
    Arrays.sort(playerFriendlinessPoint, Collections.reverseOrder());

    long leftmostFriendlinessPoints = playerFriendlinessPoint[0];
    long rightmostFriendlinessPoints = playerFriendlinessPoint[1];

    PriorityQueue<Player> playerPairsQueue = new PriorityQueue<>(Collections.reverseOrder());
    playerPairsQueue.add(new Player(leftmostFriendlinessPoints, rightmostFriendlinessPoints));
    playerPairsQueue.add(new Player(leftmostFriendlinessPoints, rightmostFriendlinessPoints));

    
    long totalConfort = Math.max(leftmostFriendlinessPoints, rightmostFriendlinessPoints);
    for (int i = 2; i < playerFriendlinessPoint.length; i++) {
      Player currentPair = playerPairsQueue.poll();
      long newPlayerFriendliness = playerFriendlinessPoint[i];

      totalConfort += currentPair.minimumFriendliness;

      playerPairsQueue.add(new Player(currentPair.leftFriendliness, newPlayerFriendliness));
      playerPairsQueue.add(new Player(newPlayerFriendliness, currentPair.rightFriendliness));
    }
    out.println(totalConfort);
  }

  public long parseStringToLong(String c){
    return Long.parseLong(c);
  }


  public class Player implements Comparable<Player>{
    public long leftFriendliness;
    public long rightFriendliness;

    public long minimumFriendliness;

    public Player(long leftFriendliness, long rightFriendliness) {
      this.leftFriendliness = leftFriendliness;
      this.rightFriendliness = rightFriendliness;

      minimumFriendliness = Math.min(leftFriendliness, rightFriendliness);

    }

    @Override
    public int compareTo(Player o) {
      long diff = this.minimumFriendliness - o.minimumFriendliness;
      if (diff > 0){
        return 1;
      } else if (diff == 0){
        return 0;
      }else {
        return -1;
      }
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
