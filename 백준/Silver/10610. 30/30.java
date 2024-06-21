import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String num = sc.next();
    int length = num.length();

    if (length == 1) {
      System.out.println(-1);
      System.exit(0);
    }

    int[] cnt = new int[10];
    int sum = 0;
    for (int i = 0; i < length; i++) {
      int cur = num.charAt(i) - '0';
      cnt[cur]++;
      sum += cur;
    }

    if (cnt[0] == 0 || sum % 3 != 0) {
      System.out.println(-1);
    } else {
      StringBuilder answer = new StringBuilder();
      for (int i = 9; i >= 0; i--) {
        for (int j = 0; j < cnt[i]; j++) answer.append(i);
      }
      System.out.println(answer);
    }
  }
}
