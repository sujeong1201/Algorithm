import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    String target = br.readLine();
    int[] alphabetCnt = getAlphabetCnt(target);
    int targetLength = target.length();

    int answer = 0;
    while (--N > 0) {
      String word = br.readLine();
      int[] curCnt = getAlphabetCnt(word);
      if (compareWords(targetLength, word.length(), alphabetCnt, curCnt)) answer++;
    }

    System.out.println(answer);
  }

  private static boolean compareWords(int length1, int length2, int[] alphabetCnt, int[] curCnt) {
    if (Math.abs(length1 - length2) >= 2) return false;

    int diff = 0;
    for (int i = 0; i < 26; i++) {
      if (alphabetCnt[i] == curCnt[i]) continue;
      diff += Math.abs(alphabetCnt[i] - curCnt[i]);
      if (diff > 2) return false;
    }

    return true;
  }

  private static int[] getAlphabetCnt(String target) {
    int[] result = new int[26];

    for (int i = 0; i < target.length(); i++) {
      result[target.charAt(i) - 'A']++;
    }

    return result;
  }
}
