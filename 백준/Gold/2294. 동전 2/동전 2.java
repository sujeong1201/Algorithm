import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] coins = new int[N];
    for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());

    int[] dp = new int[K + 1];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 1; j <= K; j++) {
        if (j >= coins[i]
            && dp[j - coins[i]] != -1
            && (dp[j] == -1 || dp[j] > dp[j - coins[i]] + 1)) dp[j] = dp[j - coins[i]] + 1;
      }
    }

    System.out.println(dp[K]);
  }
}
