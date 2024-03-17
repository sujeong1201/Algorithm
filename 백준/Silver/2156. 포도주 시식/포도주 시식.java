import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] volume = new int[N];
        for(int i = 0; i < N; i++) volume[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][3];  // 0열은 안마심, 1열은 연속1잔, 2열은 연속2잔
        dp[0][1] = volume[0];
        if(N >= 2) {
            dp[1][0] = dp[0][1];
            dp[1][1] = volume[1];
            dp[1][2] = dp[0][1] + volume[1];
        }
        if(N >= 3) {
            dp[2][0] = Math.max(Math.max(dp[1][1], dp[1][2]), dp[0][1]);
            dp[2][1] = dp[0][1] + volume[2];
            dp[2][2] = dp[1][1] + volume[2];
        }

        for(int i = 2; i < N; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), Math.max(dp[i - 2][1], dp[i - 2][2]));
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 2][1], dp[i - 2][2])) + volume[i];
            dp[i][2] = dp[i - 1][1] + volume[i];
        }

        System.out.println(Math.max(Math.max(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
    }
}
