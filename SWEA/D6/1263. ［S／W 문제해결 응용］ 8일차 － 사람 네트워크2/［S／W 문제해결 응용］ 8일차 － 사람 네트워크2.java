import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] distance;
	static final int INF = 1_000_001;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			distance = new int[N][N];
			
			for(int r=0; r<N; r++) for(int c=0; c<N; c++) 
				distance[r][c] = Integer.parseInt(st.nextToken());
			
			floyd();
			int min = Integer.MAX_VALUE;
			for(int r=0; r<N; r++) {
				int sum = 0;
				for(int c=0; c<N; c++) sum += distance[r][c];
				
				min = Math.min(min, sum);
			}
			
			sb.append("#" + t + " " + min + "\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void floyd() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(r != c && distance[r][c] == 0) distance[r][c] = INF;
			}
		}
		
		for(int k=0; k<N; k++) {  // 경유지
			for(int i=0; i<N; i++) {  // 출발지
				if(i == k) continue;
				
				for(int j=0; j<N; j++) {  // 도착지
					if(i == j || j == k) continue;
					
					if(distance[i][j] > distance[i][k] + distance[k][j]) 
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
	}
}
