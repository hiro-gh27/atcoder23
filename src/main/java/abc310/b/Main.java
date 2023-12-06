package abc310.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.*;

public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private static PrintWriter out;

  public static void main(String[] args) {
    LOGGER.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SingleLineFormatter());
    LOGGER.addHandler(handler);

    Main main = new Main();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    try {
      main.run(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    out.close();
  }

  class ElectricAppliance implements Comparable<ElectricAppliance>{
    int price;
    HashSet<Integer> functions;

    public ElectricAppliance(int price, HashSet<Integer> functions) {
      this.price = price;
      this.functions = functions;
    }

    @Override
    public int compareTo(ElectricAppliance o) {
      if (price != o.price){
        return price - o.price;
      }
      return functions.size() - o.functions.size();
    }
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    int M = sc.nextInt();

    ArrayList<ElectricAppliance> applianceArrayList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      int price = sc.nextInt();
      int C = sc.nextInt();
      HashSet<Integer> functionsList = new HashSet<>();
      for (int j = 0; j < C; j++) {
        functionsList.add(sc.nextInt());
      }
      ElectricAppliance electricAppliance = new ElectricAppliance(price, functionsList);
      applianceArrayList.add(electricAppliance);
    }

    Collections.sort(applianceArrayList);
    for (int i = 0; i < applianceArrayList.size(); i++) {
      ElectricAppliance ea1 = applianceArrayList.get(i);
      for (int j = i+1; j < applianceArrayList.size(); j++) {
        ElectricAppliance ea2 = applianceArrayList.get(j);
        if (ea2.price >= ea1.price){
          HashSet<Integer> min = ea1.functions.size() > ea2.functions.size() ? ea2.functions : ea1.functions;
          HashSet<Integer> max = ea1.functions.size() > ea2.functions.size() ? ea1.functions : ea2.functions;
          boolean isContains = true;
          for (Integer func : min) {
            if (!max.contains(func)){
              isContains = false;
              break;
            }
          }
          if (isContains){
            if (ea2.price > ea1.price || max.size() > min.size()){
              out.println("Yes");
              return;
            }

          }
          
        }


      }
    }

    out.println("No");
  }

  public ArrayList<Character> generateLowercaseAlphabeticList() {
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'a'; i <= 'z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  public ArrayList<Character> generateUppercaseAlphabeticList() {
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'A'; i <= 'Z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  // logger 
  static class SingleLineFormatter extends Formatter {

    private static final String format = "[%1$tF %1$tT] %2$s %n";

    @Override
    public String format(LogRecord record) {
      return String.format(format,
          new java.util.Date(record.getMillis()),
          record.getMessage()
      );
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
