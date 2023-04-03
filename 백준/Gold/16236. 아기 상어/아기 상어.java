import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static Point sharkPos;
	static int sharkSize, count, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		sharkSize = 2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					sharkPos = new Point(i, j);
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			if(!bfs()) break;
			count++;
			
			if(count == sharkSize) {
				sharkSize++;
				count = 0;
			}
		}
		
		System.out.println(ans);
	}

	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	private static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(sharkPos);
		visited[sharkPos.r][sharkPos.c] = true;
		int time = 0;
		Point find = null;
		
		while(!q.isEmpty()) {
			for(int size=q.size(), s=0; s<size; s++) {
				Point current = q.poll();
				
				for(int d=0; d<4; d++) {
					int nr = current.r + dr[d], nc = current.c + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(visited[nr][nc]) continue;
					
					if(map[nr][nc] == 0 || map[nr][nc] == sharkSize) {
						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
					} else if(map[nr][nc] < sharkSize) {
						if(find == null) {
							find = new Point(nr, nc);
						} else {
							if(nr < find.r) {
								find = new Point(nr, nc);
							} else if(nr == find.r) {
								if(nc < find.c) find = new Point(nr, nc);
							}
						}
					}
				}
			}
			
			time++;
			if(find != null) break;
		}
		
		if(find != null) {
			sharkPos.r = find.r;
			sharkPos.c = find.c;
			ans += time;
			map[sharkPos.r][sharkPos.c] = 0;
			return true;
		}
		return false;
	}
}
