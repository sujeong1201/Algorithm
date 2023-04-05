import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
			return "Point [r=" + r + ", c=" + c + ", type=" + type + "]";
		}
	}

	static int R, C;
	static char[][] map;
	static Point position;
	static List<Point> waterNext;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static boolean[][] visited;  // 고슴도치 위치만 방문체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		waterNext = new ArrayList<Point>();

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if (map[r][c] == 'S') {
					position = new Point(r, c, 'S');
					map[r][c] = '.';
					break;
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '*') {
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						if(map[nr][nc] == 'D' || map[nr][nc] == 'X') continue;
						
						map[nr][nc] = '#';  // 다음 시간에 물이 찰 예정인 칸은 # 로 표시
						waterNext.add(new Point(nr, nc, '#'));
					}
				}
			}
		}

		int time = bfs();
		if(time == -1) System.out.println("KAKTUS");
		else System.out.println(time);
	}

	private static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(position);
		visited[position.r][position.c] = true;
		for(Point p : waterNext) {
			queue.offer(p);
		}
		
		int level = 1;
		Point cur = null;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				cur = queue.poll();
				
				switch(cur.type) {
				case 'S':
					for(int d=0; d<4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];
						if(nr < 0 || nr >=R || nc < 0 || nc >= C) continue;
						if(map[nr][nc] == 'X' || map[nr][nc] == '*' || map[nr][nc] == '#' || visited[nr][nc]) continue;
						if(map[nr][nc] == 'D') return level;
						
						queue.offer(new Point(nr, nc, 'S'));
						visited[nr][nc] = true;
					}
					break;
				case '#':
					map[cur.r][cur.c] = '*';
					for(int d=0; d<4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];
						if(nr < 0 || nr >=R || nc < 0 || nc >= C) continue;
						
						if(map[nr][nc] == '.') {
							queue.offer(new Point(nr, nc, '#'));
							map[nr][nc] = '#';
						}
					}
					break;
				}
			}
			
			level++;
		}
		
		return -1;
	}
}
