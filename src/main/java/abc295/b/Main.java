package abc295.b;

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
    int R = sc.nextInt();
    int C = sc.nextInt();

    char[][] tables = new char[R][C];

    for (int i = 0; i < R; i++) {
      tables[i] = sc.nextLine().toCharArray();
    }

    for (int i = 0; i < tables.length; i++) {
      for (int j = 0; j < tables[i].length; j++) {
        if(tables[i][j] == '#'){
          tables = detonateBomb(tables, i, j);
        }
      }
    }

    for (int i = 0; i < tables.length; i++) {
      for (int j = 0; j < tables[i].length; j++) {
        char letter = tables[i][j];
        if (letter >= '1' && letter <= '9') {
          tables[i][j] = '.';
        }
      }
    }



    printAfterTable(tables);
  }

  private void printAfterTable(char[][] tables){
    for (char[] table : tables) {
      out.println(new String(table));
    }
  }

  private char[][] detonateBomb(char[][] currentTable, int x, int y){
    for (int i = 0; i < currentTable.length; i++) {
      for (int j = 0; j < currentTable[i].length; j++) {
        if (currentTable[i][j] >= '1' && currentTable[i][j] <= '9'){
          int upperManhattan = currentTable[i][j] - '0';
          int currentManhattan = Math.abs(i-x) + Math.abs(j-y);
          if (currentManhattan <= upperManhattan){
            currentTable[x][y] = '.';
          }
        }
      }
    }
    return currentTable;
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
