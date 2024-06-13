import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static String t;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    t = br.readLine();

    recur(s);
    System.out.println(0);
  }

  private static void recur(String s) {
    if (s.length() == t.length()) {
      if (s.equals(t)) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }

      System.exit(0);
    }

    StringBuilder sb = new StringBuilder(s);
    sb.append("A");
    String nextStr = sb.toString();
    if (t.contains(nextStr) || (nextStr.length() < t.length() && t.contains(sb.reverse())))
      recur(nextStr);

    sb = new StringBuilder(s);
    sb.reverse();
    sb.append("B");
    nextStr = sb.toString();
    if (t.contains(nextStr) || (nextStr.length() < t.length() && t.contains(sb.reverse())))
      recur(nextStr);
  }
}
