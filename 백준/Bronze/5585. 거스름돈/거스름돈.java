import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int change = 1000 - sc.nextInt();

    int[] money = {500, 100, 50, 10, 5, 1};
    int cnt = 0;
    for (int i = 0; i < money.length; i++) {
      if (change >= money[i]) {
        cnt += change / money[i];
        change %= money[i];
      }
    }

    System.out.println(cnt);
  }
}
