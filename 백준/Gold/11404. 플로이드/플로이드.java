import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] distance = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(distance[from][to] == 0 || (distance[from][to] > 0 && distance[from][to] > weight)) 
				distance[from][to] = weight;
		}
		
		final int INF = 10_000_001;
		// 초기화
		for(int i=1; i<=N; i++) for(int j=1; j<=N; j++) if(i != j && distance[i][j] == 0) 
			distance[i][j] = INF;
		
		for(int k=1; k<=N; k++) { // 경유지
			for(int i=1; i<=N; i++) {  // 출발지
				if(i == k) continue;
				
				for(int j=1; j<=N; j++) {  // 도착지
					if(j == k || i == j) continue;
					
					if(distance[i][j] > distance[i][k] + distance[k][j])
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(distance[i][j] == INF) distance[i][j] = 0;
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}
	}
}
