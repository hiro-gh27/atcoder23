package abc271.c;

import java.io.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
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


  class BookShelf {

    TreeMap<Integer, Integer> books = new TreeMap<>();
    long numberOfReadBooks = 0;

    BookShelf(TreeMap<Integer, Integer> srcBooks){
      for (Entry<Integer, Integer> book : srcBooks.entrySet()) {
        books.put(book.getKey(), 1);
        if (book.getValue() >= 2){
          numberOfReadBooks += book.getValue() - 1;
        }
      }
    }

    boolean readBook(int volume) {
      if (books.containsKey(volume)) {
        books.remove(volume);
        return true;
      }

      if (numberOfReadBooks >= 2) {
        numberOfReadBooks -= 2;
        return true;
      }

      return false;
    }

    boolean readBookWithSelling(int volume){
      if (books.containsKey(volume)){
        return true;
      }

      // numberOfReadBooksは少なくとも1/0
      long diff = 2 - numberOfReadBooks;
      numberOfReadBooks = 0;

      if (books.size() <= diff){
        return false;
      }

      ArrayList<Integer> keys = new ArrayList<>();
      for (long i = 0; i < diff; i++) {
        if (keys.isEmpty()){
          keys.add(books.lastKey());
        }else {
          int last = keys.get(keys.size()-1);
          keys.add(books.lowerKey(last));
        }
      }

      if (volume < keys.get(keys.size()-1)){
        for (Integer key : keys) {
          books.remove(key);
        }
        return true;
      }

      return false;
    }
  }
  
  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();

    TreeMap<Integer, Integer> books = new TreeMap<>();
    for (int i = 0; i < N; i++) {
      int bookVolumeNumber = sc.nextInt();
      int currentCount = books.getOrDefault(bookVolumeNumber, 0);
      books.put(bookVolumeNumber, currentCount+1);
    }

    BookShelf bookShelf = new BookShelf(books);
    int FINAL_VOLUME = (int) Math.pow(10, 9);
    int ans = 0;

    // 均等化した状態で進めるところまで進む
    for (int i = 1; i <= FINAL_VOLUME; i++) {
      if (!bookShelf.readBook(i)){
        break;
      }
      ans = i;
    }

    // 最終巻付近から順番に売却する
    for (int i = ans+1; i <= FINAL_VOLUME; i++) {
      if (!bookShelf.readBookWithSelling(i)){
        break;
      }
      ans = i;
    }
    
    out.println(ans);

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
