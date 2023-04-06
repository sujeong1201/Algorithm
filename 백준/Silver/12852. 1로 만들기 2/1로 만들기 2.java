import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		int[] prev = new int[N + 1];
		dp[1] = 0;
		prev[1] = 0;
		
		for(int i=2; i<=N; i++) {
			if(i % 3 == 0) {
				dp[i] = dp[i / 3] + 1;
				prev[i] = i / 3;
			}
			if(i % 2 == 0 && (dp[i] == 0 || dp[i / 2] + 1 < dp[i])) {
				dp[i] = dp[i / 2] + 1;
				prev[i] = i / 2;
			}
			if(dp[i] == 0 || dp[i - 1] + 1 < dp[i]) {
				dp[i] = dp[i - 1] + 1;
				prev[i] = i - 1;
			}
		}
		
		System.out.println(dp[N]);
		for(int i=N; i>=1; i=prev[i]) {
			System.out.print(i + " ");
		}
	}
}
