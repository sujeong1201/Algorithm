import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        dp = new int[10_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solve(N) + "\n");
        }
        System.out.println(sb);
    }

    private static int solve(int n) {
        if(dp[n] == 0) {
            dp[n] = solve(n - 1) + solve(n - 2) - solve(n - 3);
            if(n % 3 == 0) dp[n] += 1;
        }

        return dp[n];
    }
}
