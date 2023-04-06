import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][];
	static List<int[]> virus;
	static int numbers[], answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<int[]>();
		numbers = new int[M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) * (-1);
				if (map[r][c] == -2) {
					virus.add(new int[] { r, c });
					map[r][c] = 0;
				}
			}
		}

		answer = Integer.MAX_VALUE;
		comb(0, 0);
		
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}


	private static void comb(int cnt, int start) {
		if (cnt == M) {
			int[][] mapCopy = new int[N][];
			for(int r=0; r<N; r++) mapCopy[r] = map[r].clone();
			
			bfs(mapCopy);
			return;
		}

		for (int i = start; i < virus.size(); i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static void bfs(int[][] map) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		for (int i = 0; i < M; i++) {
			queue.offer(new int[] { virus.get(numbers[i])[0], virus.get(numbers[i])[1] });
			map[virus.get(numbers[i])[0]][virus.get(numbers[i])[1]] = -2;
		}
		
		if(finish(map)) {
			answer = Math.min(answer, 0);
			return;
		}

		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				
				for(int d=0; d<4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(map[nr][nc] != 0) continue;
					
					map[nr][nc] = level;
					queue.offer(new int[] {nr, nc});
				}
			}
			
			if(finish(map)) {
				answer = Math.min(answer, level);
				return;
			}
			
			level++;
		}
	}


	private static boolean finish(int[][] map) {
		for(int r=0; r<N; r++) for(int c=0; c<N; c++) if(map[r][c] == 0) return false;
		
		return true;
	}
}

