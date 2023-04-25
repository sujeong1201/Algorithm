import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][];
	static Curve[] curveArr;
	
	static class Curve {
		int x, y, dir, gen;
		List<int[]> pointList;
		
		public Curve(int r, int c, int dir, int gen) {
			this.x = r;
			this.y = c;
			this.dir = dir;
			this.gen = gen;
			
			pointList = new ArrayList<>();
			pointList.add(new int[] {r, c});
			map[r][c] = 1;
			if(dir == 0) {
				map[r][c + 1] = 1;
				pointList.add(new int[] {r, c + 1});
			} else if(dir == 1) {
				map[r - 1][c] = 1;
				pointList.add(new int[] {r - 1, c});
			} else if(dir == 2) {
				map[r][c - 1] = 1;
				pointList.add(new int[] {r, c - 1});
			} else {
				map[r + 1][c] = 1;
				pointList.add(new int[] {r + 1, c});
			}
			
			makeCurve();
		}

		private void makeCurve() {
			int cnt = 0;
			while(cnt++ < gen) {
				int size = pointList.size();
				int[] cur = pointList.get(size - 1);
				int[] next;
				for(int i = 2; i <= size; i++) {
					next = pointList.get(size - i);
					int[] tmp = pointList.get(size + i - 3);
					if(cur[0] == next[0] && cur[1] < next[1]) {
						pointList.add(new int[] {tmp[0] + 1, tmp[1]});
						map[tmp[0] + 1][tmp[1]] = 1;
					} else if(cur[0] == next[0] && cur[1] > next[1]) {
						pointList.add(new int[] {tmp[0] - 1, tmp[1]});
						map[tmp[0] - 1][tmp[1]] = 1;
					} else if(cur[0] < next[0] && cur[1] == next[1]) {
						pointList.add(new int[] {tmp[0], tmp[1] - 1});
						map[tmp[0]][tmp[1] - 1] = 1;
					} else if(cur[0] > next[0] && cur[1] == next[1]) {
						pointList.add(new int[] {tmp[0], tmp[1] + 1});
						map[tmp[0]][tmp[1] + 1] = 1;
					}
					cur = next;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		curveArr = new Curve[N];
		map = new int[101][101];
		
		int x, y, dir, gen;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());
			curveArr[i] = new Curve(y, x, dir, gen);
		}
		
		System.out.println(findSquare());
	}

	private static int findSquare() {
		int cnt = 0;
		int[][] delta = {{0, 1}, {1, 0}, {1, 1}};
		for(int r = 0; r < 100; r++) {
			for(int c = 0; c < 100; c++) {
				if(map[r][c] == 1) {
					boolean flag = true;
					for(int d = 0; d < 3; d++) {
						int nr = r + delta[d][0];
						int nc = c + delta[d][1];
						
						if(map[nr][nc] == 0) {
							flag = false;
							break;
						}
					}
					
					if(flag) cnt++;
				}
			}
		}
		
		return cnt;
	}
}