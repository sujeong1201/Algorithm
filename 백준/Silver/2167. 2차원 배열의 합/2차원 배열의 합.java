import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sum[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = 0;
			for(int j = 1; j <= M; j++) {
				tmp += Integer.parseInt(st.nextToken());
				sum[i][j] = tmp;
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			for(int r = i; r <= x; r++) {
				ans += (sum[r][y] - sum[r][j - 1]);
			}
			sb.append(ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
