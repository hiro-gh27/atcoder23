package arc122.b;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

  class Insurance{
    ArrayList<Integer> payments;
    public Insurance(ArrayList<Integer> payments) {
      this.payments = payments;
    }

    public double dividedThree(double left, double right){

      double oneThird = (right - left) / 3;
      double reducedLeftHandPoint = left + oneThird;
      double reducedRightHandPoint = right - oneThird;

      double reducedLeftHandPayment = totalPayment(reducedLeftHandPoint);
      double reducedRightHandPayment = totalPayment(reducedRightHandPoint);

      if (reducedRightHandPoint - reducedLeftHandPoint < Math.pow(10, -6)){
        return reducedLeftHandPoint;
      }

      

      if (reducedLeftHandPayment < reducedRightHandPayment){
        return dividedThree(left, reducedRightHandPoint);
      } else {
        return dividedThree(reducedLeftHandPoint, right);
      }

    }

    public double totalPayment(double insuranceFee){
      double total = 0;
      for (Integer payment : payments) {
        double current = insuranceFee + payment - Math.min(payment, insuranceFee*2);
        total += current;
      }
      return total;
    }
  }


  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    ArrayList<Integer> payments = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      payments.add(sc.nextInt());
    }
    Collections.sort(payments);
    Insurance insurance = new Insurance(payments);

    double point = insurance.dividedThree(0, Math.pow(10, 9));
    double result = insurance.totalPayment(point)/payments.size();
    out.println(String.format("%.20f", result));

  }

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
