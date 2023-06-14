import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, adj[][], numbers[];
	static int minDis, answer[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adj = new int[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a][b] = adj[b][a] = 1;
		}
		
		minDis = Integer.MAX_VALUE;
		answer = new int[2];
		numbers = new int[2];
		comb(0, 0);  // 건물 2개 고르기
		
		System.out.print(answer[0] + " " + answer[1] + " " + minDis);
	}

	private static void comb(int start, int cnt) {
		if(cnt == 2) {
			bfs();  // 치킨집까지 최단시간 구하기
			return;
		}
		
		for(int i = start + 1; i < N; i++) {
			numbers[cnt] = i;
			comb(start + 1, cnt + 1);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		
		queue.offer(numbers[0]);
		visited[numbers[0]] = true;
		queue.offer(numbers[1]);
		visited[numbers[1]] = true;
		
		int level = 0;
		int sum = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				int cur = queue.poll();
				sum += level * 2;
				
				for(int i = 0; i < N; i++) {
					if(i == cur || visited[i] || adj[cur][i] == 0) continue;
					queue.offer(i);
					visited[i] = true;
				}
			}
			
			if(sum >= minDis) return;
			level++;
		}
		
		minDis = sum;
		answer[0] = numbers[0];
		answer[1] = numbers[1];
	}
}
