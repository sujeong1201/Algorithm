import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int answer = 0;
    outer:
    while (N-- > 0) {
      String word = br.readLine();

      char ch = word.charAt(0);
      boolean[] check = new boolean[26];
      for (int i = 0; i < word.length(); i++) {
        char cur = word.charAt(i);
        if (ch != cur) {
          if (check[ch - 'a']) continue outer;

          check[ch - 'a'] = true;
          ch = cur;
        }
      }

      if (!check[ch - 'a']) answer++;
    }

    System.out.println(answer);
  }
}
