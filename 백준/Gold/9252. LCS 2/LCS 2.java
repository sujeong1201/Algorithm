import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static String str1, str2;
  static int length1, length2;
  static int lcsLength[][];
  static String lcsStr[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    str1 = br.readLine();
    str2 = br.readLine();
    length1 = str1.length();
    length2 = str2.length();
    lcsStr = new String[length1][length2];
    lcsLength = new int[length1][length2];
    for (int i = 0; i < length1; i++) Arrays.fill(lcsLength[i], -1);

    System.out.println(getLcs(length1 - 1, length2 - 1));
    if (lcsLength[length1 - 1][length2 - 1] > 0)
      System.out.println(lcsStr[length1 - 1][length2 - 1]);
  }

  private static int getLcs(int i, int j) {
    if (i == -1 || j == -1) {
      return 0;
    }

    if (lcsLength[i][j] == -1) {
      if (str1.charAt(i) == str2.charAt(j)) {
        lcsLength[i][j] = getLcs(i - 1, j - 1) + 1;
        if (i > 0 && j > 0) lcsStr[i][j] = lcsStr[i - 1][j - 1] + str1.charAt(i);
        else lcsStr[i][j] = str1.charAt(i) + "";
      } else {
        int value1 = getLcs(i - 1, j);
        int value2 = getLcs(i, j - 1);
        if (value1 > value2) {
          lcsLength[i][j] = value1;
          if (i > 0) lcsStr[i][j] = lcsStr[i - 1][j];
          else lcsStr[i][j] = "";
        } else {
          lcsLength[i][j] = value2;
          if (j > 0) lcsStr[i][j] = lcsStr[i][j - 1];
          else lcsStr[i][j] = "";
        }
      }
    }

    return lcsLength[i][j];
  }
}
