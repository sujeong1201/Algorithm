import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String[] alphabets = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    Scanner sc = new Scanner(System.in);
    String str = sc.next();

    int cnt = 0;
    outer:
    for (int i = 0; i < str.length(); ) {
      cnt++;

      if (i + 1 < str.length()) {
        String substring = str.substring(i, i + 2);
        for (int j = 0; j < 8; j++) {
          if (substring.equals(alphabets[j])) {
            i += 2;
            continue outer;
          }
        }
      }

      if (i + 2 < str.length() && str.substring(i, i + 3).equals(alphabets[2])) {
        i += 3;
      } else {
        i++;
      }
    }

    System.out.println(cnt);
  }
}
