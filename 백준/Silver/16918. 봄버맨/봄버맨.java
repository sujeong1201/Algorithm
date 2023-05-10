import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, N, map[][];
	static int[] dr = {0, 1, -1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			char[] in = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if(in[c] == 'O') map[r][c] = 2;
			}
		}
		solve();
	}
	
	public static void solve() {
		boolean isBoom = false;  // 폭발할 차례인지(true) 설치할 차례인지(false)
		
		for(int i = 0; i < N - 1; i++) {
			if(isBoom) {
				for(int r = 0; r < R; r++) for(int c = 0; c < C; c++) map[r][c]++;
				boom();
			} else {
				for(int r = 0; r < R; r++) {
					for(int c = 0; c < C; c++) {
						if(map[r][c] == 4 || map[r][c] == 5) map[r][c] = 1;
						else map[r][c]++;
					}
				}
			}
			
			isBoom = !isBoom;
		}
		print();
	}
	
	private static void boom() {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 4) {
					for(int d = 1; d < 5; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						
						if(map[nr][nc] > 0 && map[nr][nc] < 4) map[nr][nc] = 5;
					}
				}
			}
		}
	}

	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] > 0 && map[r][c] < 4) sb.append("O");
				else sb.append(".");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
