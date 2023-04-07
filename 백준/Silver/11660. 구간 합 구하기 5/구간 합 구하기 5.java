import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][N + 1];
		long[][] preSum = new long[N + 1][N + 1]; // 행별로 누적합 구하기

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			long sum = 0;
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
				preSum[i][j] = sum;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int rA = Integer.parseInt(st.nextToken());
			int cA = Integer.parseInt(st.nextToken());
			int rB = Integer.parseInt(st.nextToken());
			int cB = Integer.parseInt(st.nextToken());
			
			long sum = 0;
			for(int r=rA; r<=rB; r++) {
				sum += (preSum[r][cB] - preSum[r][cA - 1]);
			}
			
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
