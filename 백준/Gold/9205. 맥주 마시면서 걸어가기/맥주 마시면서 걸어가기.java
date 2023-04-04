import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;  // 편의점 개수
	static Point[] points;
	static int[][] distance;
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			points = new Point[N + 2];
			distance = new int[N + 2][N + 2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			points[N + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for(int i=0; i<N+2; i++) {
				for(int j=0; j<N+2; j++) {
					if(i == j) {
						distance[i][j] = 0;
						continue;
					}
					
					int dis = dis(points[i], points[j]);
					if(dis > 1000) distance[i][j] = INF;
					else distance[i][j] = dis;
				}
			}
			
			floyd();
			
			if(distance[0][N + 1] == INF) System.out.println("sad");
			else System.out.println("happy");
		}
	}
	
	private static void floyd() {
		for(int k=0; k<N+2; k++) {  // 경유지
			for(int i=0; i<N+2; i++) {  // 출발지
				if(i == k) continue;
				
				for(int j=0; j<N+2; j++) {  // 도착지
					if(j == k || i == j) continue;
					
					if(distance[i][j] > distance[i][k] + distance[k][j]) 
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
	}
	
	private static int dis(Point a, Point b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}
}
