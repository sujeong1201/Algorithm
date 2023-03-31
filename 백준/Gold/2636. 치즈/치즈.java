import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C;
	static int[][] map;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		int hour = 0;
		int cnt = 0;
		while(true) {
			bfs();  // 구멍이 아닌 0인 영역 구하기
			cnt = melt();  // 공기와 접촉된 치즈 녹이기
			
			hour++;
			if(check()) break;
		}
		
		System.out.println(hour);
		System.out.println(cnt);
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		int r = 0, c = 0;
		queue.offer(new Point(r, c));
		map[r][c] = 2;
		
		Point cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			r = cur.r;
			c = cur.c;
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0 || map[nr][nc] == 2) {
					queue.offer(new Point(nr, nc));
					map[nr][nc] = 2;
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	private static int melt() {
		int cnt = 0;
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == 1) {
					for(int d=0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						
						if(map[nr][nc] == 2) {
							map[r][c] = 0;
							cnt++;
							break;
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean check() {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == 1) return false;
			}
		}
		return true;
	}
}
