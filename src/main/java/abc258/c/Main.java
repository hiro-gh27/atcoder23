package abc258.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

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

  public class Query {
    private int queryId;
    private int argument;

    public Query(String queryId, String argument){
      this.queryId = Integer.parseInt(queryId);
      this.argument = Integer.parseInt(argument);
    }

    public int getArgument() {
      return argument;
    }

    public int getQueryId() {
      return queryId;
    }
  }

  /*
  public class QueryExecutor {

    public char[] execute(char[] originLetters, Query query){
      switch (query.getQueryId()){
        case 1:
          return swap(originLetters, query.argument);
        case 2:
          out.println(originLetters[query.argument-1]);
          break;
      }
      return originLetters;
    }

    public char[] swap(char[] letters, int lastN){
      char[] firstPart = Arrays.copyOfRange(letters, 0, letters.length - lastN);
      char[] secondPart = Arrays.copyOfRange(letters, letters.length - lastN, letters.length);

      for (int i = 0; i < secondPart.length/2; i++) {
        char tmp = secondPart[i];
        secondPart[i] = secondPart[secondPart.length -1 -i];
        secondPart[secondPart.length -1 -i] = tmp;
      }

      char[] result = new char[letters.length];
      System.arraycopy(secondPart, 0, result, 0, secondPart.length);
      System.arraycopy(firstPart, 0, result, secondPart.length, firstPart.length);
      return result;
    }
  }
  */

  private ArrayList<Query> readInputQueries(MyScanner sc, int length) {
    ArrayList<Query> queries = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      String[] inputLineWords = sc.nextLine().split(" ");
      queries.add(new Query(inputLineWords[0], inputLineWords[1]));
    }
    return queries;
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int wordLength = sc.nextInt();
    int numberOfQuery = sc.nextInt();
    char[] inputLetters = sc.nextLine().toCharArray();
    ArrayList<Query> inputQueries = readInputQueries(sc, numberOfQuery);

    int pointer = 0;
    for (Query query : inputQueries) {
      if (query.getQueryId() == 1){
        pointer += query.getArgument();
        pointer = pointer % wordLength;
      } else {
        int index = ((wordLength - 1 - pointer) + query.getArgument())%wordLength;
        out.println(inputLetters[index]);
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
