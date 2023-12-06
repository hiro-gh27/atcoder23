package arc071.a;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  /**
   * アルファベットa~zをカウントするためのMapを0で初期化して返します。
   * @return
   */
  public HashMap<Character, Integer> initCharacterMapZero(){
    HashMap<Character, Integer> map = new HashMap<>();
    for (Character character : generateLowercaseAlphabeticList()) {
      map.put(character, 0);
    }
    return map;
  }

  /**
   * 入力された文字列をアルファベットa~zに分解してカウントします。
   * @param input
   * @return
   */
  public HashMap<Character, Integer> countCharacterInStrings(String input){
    HashMap<Character, Integer>  characterCountMap = initCharacterMapZero();
    for (char letter : input.toCharArray()) {
      characterCountMap.put(letter, characterCountMap.get(letter)+1);
    }
    return characterCountMap;
  }

  public ArrayList<Character> generateLowercaseAlphabeticList(){
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'a'; i <= 'z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  public ArrayList<Character> generateUppercaseAlphabeticList(){
    ArrayList<Character> alphabeticList = new ArrayList<>();
    for (int i = 'A'; i <= 'Z'; i++) {
      alphabeticList.add((char) i);
    }
    return alphabeticList;
  }

  /**
   * アルファベットa~zの各個数をカウント済みのリストを受取って共通部分を見つけます。
   * @param countCharInStringsList
   * @return
   */
  public String findCommonCharacters(List<HashMap<Character, Integer>> countCharInStringsList){
    StringBuilder sb = new StringBuilder();
    for (Character lowercaseAlphabetic : generateLowercaseAlphabeticList()) {
      int minOccerance = 100;
      for (HashMap<Character, Integer> characterIntegerHashMap : countCharInStringsList) {
        int encounter = characterIntegerHashMap.get(lowercaseAlphabetic);
        minOccerance = Math.min(minOccerance, encounter);
      }
      String addition = String.valueOf(lowercaseAlphabetic).repeat(minOccerance);
      sb.append(addition);

    }
    return sb.toString();
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int n = sc.nextInt();
    ArrayList<HashMap<Character, Integer>> characterInStringsList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      characterInStringsList.add(countCharacterInStrings(sc.next()));
    }
    out.println(findCommonCharacters(characterInStringsList));
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
