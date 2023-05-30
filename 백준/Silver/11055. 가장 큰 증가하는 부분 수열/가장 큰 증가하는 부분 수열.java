import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		dp[0] = arr[0];
		
		for(int i = 1; i < N; i++) {
			int tmp = 0;
			for(int j = i - 1; j >= 0; j--) {
				if(arr[i] > arr[j]) {
					if(tmp < arr[i] + dp[j]) tmp = arr[i] + dp[j];
				}
			}
			
			dp[i] = tmp == 0 ? arr[i] : tmp;
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) if(dp[i] > answer) answer = dp[i];
		System.out.println(answer);
	}
}
