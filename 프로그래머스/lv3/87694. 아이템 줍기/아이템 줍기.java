import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int[][] map;
    static int R, C;
    static int[] dr = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    	for(int i = 0; i < rectangle.length; i++) {
			R = Math.max(R, rectangle[i][3]);
			C = Math.max(C, rectangle[i][2]);
		}
    	R = 2 * R + 2;
    	C = 2 * C + 2;
		map = new int[R][C];
		
		for(int i = 0; i < rectangle.length; i++) {
			for(int r = rectangle[i][1] * 2; r <= rectangle[i][3] * 2; r++) {
				if(r == rectangle[i][1] * 2 || r == rectangle[i][3] * 2) 
					for(int c = rectangle[i][0] * 2; c <= rectangle[i][2] * 2; c++) {
						map[r][c] = 1;
					}
				else 
					map[r][rectangle[i][0] * 2] = map[r][rectangle[i][2] * 2] = 1;
			}
		}
		
		makeOut();  // 바깥 공간 잡아주기
		return bfs(new int[] {characterY * 2, characterX * 2}, 
				new int[] {itemY * 2, itemX * 2}) / 2;  // 최소 시간 구하기
    }
    
    private static void makeOut() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(map[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
					map[nr][nc] = 9;
				}
			}
		}
	}
	
	private static int bfs(int[] character, int[] item) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(character);
		visited[character[0]][character[1]] = true;
		
		int time = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					
					if(nr < 0 || nr > R || nc < 0 || nc > C) continue;
					if(visited[nr][nc]) continue;
					if(nr == item[0] && nc == item[1]) return time;
					if(map[nr][nc] == 1) {
						boolean side = false;
						for(int i = 0; i < 8; i++) {
							int nnr = nr + dr[i];
							int nnc = nc + dc[i];
							if((nnr >= 0 && nnr < R && nnc >= 0 && nnc < C) && map[nnr][nnc] == 9) {
								side = true;
							}
						}
						
						if(!side) continue;
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
			
			time++;
		}
		
		return -1;
	}
}