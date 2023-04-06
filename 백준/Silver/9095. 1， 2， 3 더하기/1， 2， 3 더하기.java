import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] ans = new int[N + 1];
			
			for(int i=1; i<=N; i++) {
				if(i == 1) {
					ans[1] = 1;
					continue;
				}
				if(i == 2) {
					ans[2] = 2;
					continue;
				}
				if(i == 3) {
					ans[3] = 4;
					continue;
				}
				ans[i] = ans[i-3] + ans[i-2] + ans[i-1];
			}
			
			System.out.println(ans[N]);
		}
	}
}
