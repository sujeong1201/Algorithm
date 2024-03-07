import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new long[K + 1][N + 1];
        for(int i = 0; i <= N; i++) dp[1][i] = 1;
        solve(K, N);
        System.out.println(dp[K][N]);
    }

    public static long solve(int cnt, int sum) {
        if(dp[cnt][sum] == 0) {
            for(int i = 0; i <= sum; i++) {
                dp[cnt][sum] += solve(cnt - 1, i);
            }
            dp[cnt][sum] %= 1_000_000_000;
        }

        return dp[cnt][sum];
    }
}
