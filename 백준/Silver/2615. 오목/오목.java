import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[19][19];
	static int[] dr = {1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<19; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean blackWin = false;
		boolean whiteWin = false;
		int r = 0, c = 0;
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				if(map[i][j] != 0 && omok(i, j)) {
					if(map[i][j] == 1) blackWin = true;
					else whiteWin = true;
					
					r = i; 
					c = j;
				}
			}
		}
		
		if(blackWin) System.out.print(1 + "\n" + (r + 1) + " " + (c + 1));
		else if(whiteWin) System.out.println(2 + "\n" + (r + 1) + " " + (c + 1));
		else System.out.println(0);
	}

	private static boolean omok(int r, int c) {
		int color = map[r][c];
		
		for(int d=0; d<4; d++) {
			int nr = r;
			int nc = c;
			int cnt = 1;
			while(true) {
				nr = nr + dr[d];
				nc = nc + dc[d];
				if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) break;
				if(map[nr][nc] != color) break;
				
				cnt++;
				// 한 지점에서 오목과 육목이 겹쳐 있을 수 있기 때문에  바로 리턴하면 안됨
				if(cnt >= 6) break;
			}
			
			if(cnt == 5) {
				nr = r - dr[d];
				nc = c - dc[d];
				if((nr >= 0 && nr < 19 && nc >= 0 && nc < 19 && map[nr][nc] != color)
						|| (nr < 0 || nr >= 19 || nc < 0 || nc >= 19)) {
					return true;
				}
			}
		}
		
		return false;
	}
}