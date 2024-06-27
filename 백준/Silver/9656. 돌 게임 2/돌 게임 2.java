import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    boolean[] dp = new boolean[N + 1]; // 상근 이기면 true, 창영 이기면 false
    dp[1] = false;
    if (N >= 2) dp[2] = true;
    if (N >= 3) dp[3] = false;
    for (int i = 4; i <= N; i++) {
      if (!dp[i - 1] || !dp[i - 3]) dp[i] = true;
      else dp[i] = false;
    }

    System.out.println(dp[N] ? "SK" : "CY");
  }
}
