import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, prices[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    prices = new int[N][3];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) prices[i][j] = Integer.parseInt(st.nextToken());
    }

    int min = Integer.MAX_VALUE;
    // 1번 집이 빨강일 때
    int[][] dp = new int[N][3];
    dp[0][0] = prices[0][0];
    dp[0][1] = dp[0][2] = -1;
    calcDp(dp);
    min = Math.min(dp[N - 1][1], dp[N - 1][2]);

    // 1번 집이 초록일 때
    dp = new int[N][3];
    dp[0][1] = prices[0][1];
    dp[0][0] = dp[0][2] = -1;
    calcDp(dp);
    if (Math.min(dp[N - 1][0], dp[N - 1][2]) < min) min = Math.min(dp[N - 1][0], dp[N - 1][2]);

    // 1번 집이 파랑일 때
    dp = new int[N][3];
    dp[0][2] = prices[0][2];
    dp[0][0] = dp[0][1] = -1;
    calcDp(dp);
    if (Math.min(dp[N - 1][0], dp[N - 1][1]) < min) min = Math.min(dp[N - 1][0], dp[N - 1][1]);

    System.out.println(min);
  }

  private static void calcDp(int[][] dp) {
    for (int i = 1; i < N; i++) {
      int[][] num = {{1, 2}, {0, 2}, {0, 1}};

      for (int j = 0; j < 3; j++) {
        if (dp[i - 1][num[j][0]] == -1 && dp[i - 1][num[j][1]] == -1) {
          dp[i][j] = -1;
        } else if (dp[i - 1][num[j][0]] == -1) {
          dp[i][j] = dp[i - 1][num[j][1]] + prices[i][j];
        } else if (dp[i - 1][num[j][1]] == -1) {
          dp[i][j] = dp[i - 1][num[j][0]] + prices[i][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][num[j][0]], dp[i - 1][num[j][1]]) + prices[i][j];
        }
      }
    }
  }
}
