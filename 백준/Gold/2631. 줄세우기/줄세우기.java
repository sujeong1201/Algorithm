import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] sequence = new int[N];
    for (int i = 0; i < N; i++) sequence[i] = Integer.parseInt(br.readLine());

    // 최장증가수열 구하기
    int[] dp = new int[N];
    int max = 0;
    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (sequence[i] > sequence[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
      }

      if (dp[i] > max) max = dp[i];
    }

    System.out.println(N - max);
  }
}
