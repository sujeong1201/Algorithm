import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][], answer;
	static boolean position[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			map = new int[11][11];
			position = new boolean[11];
			for(int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 11; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			recur(0, 0);
			System.out.println(answer);
		}
	}

	private static void recur(int cnt, int sum) {
		if(cnt == 11) {
			if(sum > answer) answer = sum;
			return;
		}
		
		for(int i = 0; i < 11; i++) {
			if(map[cnt][i] == 0) continue;
			if(position[i]) continue;
			
			position[i] = true;
			recur(cnt + 1, sum + map[cnt][i]);
			position[i] = false;
		}
	}
}
