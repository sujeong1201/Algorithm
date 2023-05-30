import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c;
		boolean gram;
		
		public Point(int r, int c) {
			this(r, c, false);
		}
		public Point(int r, int c, boolean gram) {
			this.r = r;
			this.c = c;
			this.gram = gram;
		}
	}
	
	static int N, M, T, map[][];
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}

	private static String bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		boolean[][] visitedForGram = new boolean[N][M];
		queue.offer(new Point(0, 0));
		visited[0][0] = true;
		
		int level = 0;
		outer:
		while(level < T) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				Point cur = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					boolean isGram = cur.gram;
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if((!isGram && visited[nr][nc]) || (isGram && visitedForGram[nr][nc]) || (!isGram && map[nr][nc] == 1)) continue;
					
					if(nr == N - 1 && nc == M - 1) break outer;
					if(map[nr][nc] == 2) {
						isGram = true;
						visited[nr][nc] = true;
					}
					queue.offer(new Point(nr, nc, isGram));
					if(!isGram) visited[nr][nc] = true;
					else visitedForGram[nr][nc] = true;
				}
			}
			
			level++;
		}
		
		if(level == T) return "Fail";
		else return level + 1 + "";
	}
}
