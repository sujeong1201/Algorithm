import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      String password = br.readLine();
      if (password.equals("end")) break;
      if (isAcceptable(password)) sb.append("<" + password + "> is acceptable.\n");
      else sb.append("<" + password + "> is not acceptable.\n");
    }

    System.out.println(sb);
  }

  private static boolean isAcceptable(String password) {
    Character[] vowels = {'a', 'e', 'i', 'o', 'u'};
    List<Character> vowelsList = new ArrayList<>(Arrays.asList(vowels));

    boolean haveVowels = false;
    char prevChar = 0;
    int vowelCnt = 0; // 모음 카운트
    int consonantCnt = 0; // 자음 카운트
    for (int i = 0; i < password.length(); i++) {
      char curChar = password.charAt(i);
      if (vowelsList.contains(curChar)) { // 모음인 경우
        haveVowels = true;
        vowelCnt++;
        consonantCnt = 0;
      } else {
        vowelCnt = 0;
        consonantCnt++;
      }

      if (vowelCnt == 3 || consonantCnt == 3) return false;
      if (prevChar == curChar && curChar != 'e' && curChar != 'o') return false;

      prevChar = curChar;
    }

    if (haveVowels) return true;
    return false;
  }
}
