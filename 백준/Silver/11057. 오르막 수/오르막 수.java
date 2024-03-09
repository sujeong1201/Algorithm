import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N + 1][10];

        dp[1][0] = 1;
        for(int j = 1; j <= 9; j++) dp[1][j] = dp[1][j - 1] + 1;
        for(int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0];
            for(int j = 1; j <= 9; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 10_007;
            }
        }

        System.out.println(dp[N][9]);
    }
}
