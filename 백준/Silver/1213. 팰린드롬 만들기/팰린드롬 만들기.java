import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String name = br.readLine();
    int[] alphabetCnt = new int[26];
    for (int i = 0; i < name.length(); i++) alphabetCnt[name.charAt(i) - 'A']++;

    boolean odd = false;
    Character oddChar = null;
    for (int i = 0; i < 26; i++) {
      if (alphabetCnt[i] % 2 == 1) {
        if (!odd) {
          odd = true;
          oddChar = (char) ('A' + i);
        } else {
          System.out.println("I'm Sorry Hansoo");
          System.exit(0);
        }
      }
    }

    StringBuilder palindrom = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < alphabetCnt[i] / 2; j++) palindrom.append((char) ('A' + i));
    }

    String answer = "";
    answer += palindrom;
    if (odd) answer += oddChar;
    palindrom.reverse();
    answer += palindrom;
    System.out.println(answer);
  }
}
