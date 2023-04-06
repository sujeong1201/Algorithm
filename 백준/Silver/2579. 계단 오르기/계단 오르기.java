import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stair = new int[N];
		for (int i = 0; i < N; i++) stair[i] = sc.nextInt();

		int[][] dp = new int[N][2];  // 0열은 연속 한개 계단, 1열은 연속 두개 계단 밟았을 때

		for (int i = 0; i < N; i++) {
			if (i == 0) {
				dp[i][0] = stair[i];
				continue;
			}
			if (i == 1) {
				dp[i][0] = stair[i];
				dp[i][1] = stair[i - 1] + stair[i];
				continue;
			}
			
			dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stair[i];
			dp[i][1] = dp[i - 1][0] + stair[i];
		}
		
		System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
	}
}
