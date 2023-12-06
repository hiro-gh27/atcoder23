package abc289.c;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

    int totalElements = sc.nextInt();
    int totalUnions = sc.nextInt();
    ArrayList<Integer[]> unions = new ArrayList<>();

    for (int i = 0; i < totalUnions; i++) {
      int unionSize = sc.nextInt();
      Integer[] S = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
      unions.add(S);
    }
    
    ArrayDeque<HashSet<Integer>> allCombinations = generateAllCombinations(unions, totalUnions);
    out.println(countTotalCoverage(allCombinations, totalElements));
  }
  
  public int countTotalCoverage(ArrayDeque<HashSet<Integer>> allCombinations, int totalElements){
    int totalCoverageCount = 0;
    while (!allCombinations.isEmpty()){
      HashSet<Integer> currentUnion = allCombinations.pop();
      if (isCoveringAllElements(currentUnion, totalElements)){
        totalCoverageCount++;
      }
    }
    return totalCoverageCount;
  }

  public boolean isCoveringAllElements(HashSet<Integer> currentUnion, int totalElements){
    // 1 ~ N
    for (int i = 1; i <= totalElements; i++) {
      if (!currentUnion.contains(i)){
        return false;
      }
    }
    return true;
  }


  public ArrayDeque<HashSet<Integer>> generateAllCombinations(ArrayList<Integer[]> unions, int totalUnions){
    ArrayDeque<HashSet<Integer>> allCombinations = new ArrayDeque<>();

    for (int i = 0; i < Math.pow(2, totalUnions); i++) {
      HashSet<Integer> currentCombinations = new HashSet<>();

      for (int j = 0; j < totalUnions; j++) {
        if ((1&i>>j) == 1){
          Integer[] currentUnions = unions.get(j);
          currentCombinations.addAll(Arrays.asList(currentUnions));
        }
      }

      if (!currentCombinations.isEmpty()){
        allCombinations.add(currentCombinations);
      }

    }
    return allCombinations;
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
