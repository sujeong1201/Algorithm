import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;
		char type;
		
		public Point(int r, int c, char type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", type=" + type + "]";
		}
	}
	
	static int R, C;
	static char[][] map;
	static Point jihoon;
	static ArrayList<Point> fires;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		fires = new ArrayList<>();
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'J') {
					jihoon = new Point(r, c, 'J');
					map[r][c] = '.';
				}
				else if(map[r][c] == 'F') {
					fires.add(new Point(r, c, 'F'));
				}
			}
		}
		
		int ans = bfs();
		if(ans != -1) System.out.println(ans);
		else System.out.println("IMPOSSIBLE");
	}

	private static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];  // 지훈이가 방문한 곳 처리
		int[] dr = {0, 0, 1, -1};
		int[] dc = {1, -1, 0, 0};
		
		for(Point fire : fires) queue.offer(fire);
		queue.offer(jihoon);
		visited[jihoon.r][jihoon.c] = true;
		
		int level = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			boolean unable = true;
			
			for(int s = 0; s < size; s++) {
				Point cur = queue.poll();
				if(cur.type == 'J' && (cur.r == 0 || cur.r == R - 1 || cur.c == 0 || cur.c == C - 1)) 
					return level;
				
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if(map[nr][nc] == '#') continue;
					
					if(cur.type == 'J') {
						if(map[nr][nc] == 'F') continue;
						if(visited[nr][nc]) continue;
						
						unable = false;
						visited[nr][nc] = true;
						queue.offer(new Point(nr, nc, 'J'));
					} else {
						if(map[nr][nc] == 'F') continue;
						
						map[nr][nc] = 'F';
						queue.offer(new Point(nr, nc, 'F'));
					}
				}
			}
			
			if(unable) return -1;
			level++;
		}
		
		return -1;
	}
}
