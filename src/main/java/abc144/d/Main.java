package abc144.d;

import java.io.*;
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

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();

    double a = sc.nextDouble();
    double b = sc.nextDouble();
    double x = sc.nextDouble();

    double originHeight = b - (x / (a * a));
    double originVolume = a * a * originHeight;
    LOGGER.info("height: " + originHeight);
    LOGGER.info("volume: " + originVolume);

    double radian = Math.toRadians(50);

    double triangleHeight = a * Math.tan(radian);
    double triangleVolume = a * a * triangleHeight / 2;
    LOGGER.info("triangleHeight: " + triangleHeight);
    LOGGER.info("triangleVolume: " + triangleVolume);

    double tiltedSquareVolume = originVolume - triangleVolume;
    double tiltedSquareHeight = tiltedSquareVolume / (a * a);
    LOGGER.info(tiltedSquareVolume + ":" + tiltedSquareHeight);

    //
    double c = 2 * x / a / b;
    LOGGER.info(String.valueOf(c));
    if (c <= a){
      // 傾けた時に下部に四角ができない
      double opposite = b;
      double adjacent = c;
      double angleInRadians = Math.atan(opposite/ adjacent);
      double digrees = Math.toDegrees(angleInRadians);
      LOGGER.info("度: " + digrees);
      out.println(digrees);
    } else {
      // 傾いた時に四角の上に三角ができる
      double S = (x / a);
      double T = (a * b) - S;
      double b2 = 2 * T / a;
      double angleInRadians = Math.atan(b2 / a);
      double digrees = Math.toDegrees(angleInRadians);
      out.println(digrees);
    }


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
