import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][], answer;
	static int[][] pattern;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[7][7];
		pattern = new int[4][2];
		pattern[0][0] = 3;
		pattern[0][1] = 3;
		visited[3][3] = true;
		makeFigure(1);
		
		System.out.println(answer);
	}

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static void makeFigure(int cnt) {
		if(cnt == 4) {
			findMax();
			return;
		}
		
		for(int i = 0; i < cnt; i++) {
			int r = pattern[i][0];
			int c = pattern[i][1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(visited[nr][nc]) continue;
				
				pattern[cnt][0] = nr;
				pattern[cnt][1] = nc;
				visited[nr][nc] = true;
				makeFigure(cnt + 1);
				visited[nr][nc] = false;
			}
		}
	}
	
	private static void findMax() {
		for(int r = 0; r < N; r++) {
			outer:
			for(int c = 0; c < M; c++) {
				int sum = 0;
				for(int i = 0; i < 4; i++) {
					int nr = r + pattern[i][0] - 3;
					int nc = c + pattern[i][1] - 3;
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue outer;
					
					sum += map[nr][nc];
				}
				if(sum > answer) answer = sum;
			}
		}
	}
}
