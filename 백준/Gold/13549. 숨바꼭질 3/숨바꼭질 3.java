import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K, ans; 
	static boolean visited[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[200_001];
		
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		ans = Integer.MAX_VALUE;
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {N, 0});
		visited[N] = true;
		int time = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 3; d++) {
				int[] next = calNext(cur[0], cur[1], d);
				if(next[0] < 0 || next[0] > 200_000) continue;
				if(visited[next[0]]) continue;
				
				if(next[0] == K) {
					ans = Math.min(ans, next[1]);
				}
				queue.offer(next);
				visited[next[0]] = true;
			}
		}
	}

	private static int[] calNext(int cur, int cnt, int d) {
		if(d == 0) {
			return new int[] {cur * 2, cnt};
		} else if(d == 1) {
            return new int[] {cur - 1, cnt + 1};
		} else {
            return new int[] {cur + 1, cnt + 1};
		}
	}
}
