import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] data = new int[N];  // 행의 인덱스
		Arrays.fill(data, N - 1);
		
		int cnt = 0;
		int ans = 0;
		while(cnt++ < N) {
			int idx = -1, tmp = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				if(tmp < map[data[i]][i]) {
					idx = i;
					tmp = map[data[i]][i];
				}
			}
			
			ans = tmp;
			data[idx]--;
		}
		
		System.out.println(ans);
	}
}
