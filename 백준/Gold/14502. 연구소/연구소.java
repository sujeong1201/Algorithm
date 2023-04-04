import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] wall;
	static List<int[]> virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wall = new int[3][];
		int answer = 0;
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) virus.add(new int[] {r, c});
			}
		}
		
		// 벽을 세울 자리 정하기
		for(int i=0; i<N*M; i++) {
			int r = i / M;
			int c = i % M;
			if(map[r][c] != 0) continue;
			
			wall[0] = new int[] {r, c};
			
			for(int j=i+1; j<N*M; j++) {
				r = j / M;
				c = j % M;
				if(map[r][c] != 0) continue;
				
				wall[1] = new int[] {r, c};
				
				for(int k=j+1; k<N*M; k++) {
					r = k / M;
					c = k % M;
					if(map[r][c] != 0) continue;
					
					wall[2] = new int[] {r, c};
					
					int[][] mapCopy = new int[N][];
					for(int x=0; x<N; x++) mapCopy[x] = map[x].clone();
					
					for(int n=0; n<3; n++) mapCopy[wall[n][0]][wall[n][1]] = 1;
					
					answer = Math.max(answer, bfs(mapCopy));
				}
			}
		}
		
		System.out.println(answer);
	}

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static int bfs(int[][] map) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][M];
		
		for(int[] v : virus) {
			queue.offer(v);
			visited[v[0]][v[1]] = true;
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc] || map[nr][nc] == 1) continue;
				
				queue.offer(new int[] {nr, nc});
				map[nr][nc] = 2;
				visited[nr][nc] = true;
			}
		}
		
		return check(map);
	}
	
	private static int check(int[][] map) {
		int cnt = 0;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == 0) cnt++;
			}
		}
		
		return cnt;
	}
}
