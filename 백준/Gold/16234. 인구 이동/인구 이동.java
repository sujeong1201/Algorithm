import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		while(true) {
			if(bfs()) break;
			ans++;
		}
		
		System.out.println(ans);
	}
	
	private static boolean bfs() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][N];
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		List<int[]> openList = new ArrayList<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(visited[r][c]) continue;
				
				openList.clear();
				queue.offer(new int[] {r, c});
				openList.add(new int[] {r, c});
				visited[r][c] = true;
				int sum = map[r][c];
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					for(int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						
						int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
						if(!visited[nr][nc] && diff >= L && diff <= R) {
							visited[nr][nc] = true;
							queue.offer(new int[] {nr, nc});
							openList.add(new int[] {nr, nc});
							sum += map[nr][nc];
						}
					}
				}
				
				if(openList.size() == 1) visited[r][c] = false;
				else fill(sum, openList);
			}
		}
		
		if(isFinish(visited)) return true;
		return false;
	}

	private static void fill(int sum, List<int[]> openList) {
		int data = sum / openList.size();
		for(int[] open : openList) {
			map[open[0]][open[1]] = data;
		}
	}
	
	private static boolean isFinish(boolean[][] visited) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(visited[r][c]) return false;  // 하나라도 true가 있으면 끝나지 않음
			}
		}
		
		return true;  // true가 하나도 없으면 끝남
	}
}
