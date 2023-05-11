import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int H, W, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H + 1][W];
		
		st = new StringTokenizer(br.readLine());
		for(int c = 0; c < W; c++) {
			int h = Integer.parseInt(st.nextToken());
			for(int r = 0; r <= h; r++) {
				map[H - r][c] = 1;
			}
		}
		
		int ans = 0;
		for(int r = H - 1; r >= 0; r--) {
			boolean left = false;
			int cnt = 0;
			for(int c = 0; c < W; c++) {
				if(map[r][c] == 1) {
					left = true;
					ans += cnt;
					cnt = 0;
				} else {
					if(left) cnt++;
				}
			}
		}
		
		System.out.println(ans);
	}
}
