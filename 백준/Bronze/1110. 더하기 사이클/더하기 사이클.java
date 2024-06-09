import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int cnt = 0;
    int num = N;
    while (true) {
      cnt++;
      int newNum = (num % 10 + num / 10) % 10 + num % 10 * 10;
      if (newNum == N) break;
      num = newNum;
    }

    System.out.println(cnt);
  }
}
