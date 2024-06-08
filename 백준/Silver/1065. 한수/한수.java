import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int cnt = 0;
    outer:
    for (int i = 1; i <= N; i++) {
      int length = (int) (Math.log10(i) + 1);
      if (length <= 2) {
        cnt++;
        continue;
      }

      int num = i;
      int n1 = num % 10;
      num /= 10;
      int n2 = num % 10;
      num /= 10;
      final int diff = n2 - n1;

      while (num > 0) {
        n1 = n2;
        n2 = num % 10;
        num /= 10;
        if (n2 - n1 != diff) continue outer;
      }

      cnt++;
    }

    System.out.println(cnt);
  }
}
