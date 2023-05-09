import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	// 기둥의 위치와 방향을 가지는 클래스
	static class Gidoong {
		List<int[]> point;
		int direction;
		
		public Gidoong() {
			point = new ArrayList<>();
		}
		
		public void rowOrCol() {  // 기둥이 어떤 방향인지 구하기
			if(point.get(0)[0] == point.get(1)[0]) direction = 0;  // 행 방향
			else direction = 1;  // 열 방향
		}
		
		public int[] getMiddle() {  // 기둥의 중심 구하기
			return point.get(1);
		}
	}
	
	static int N;
	static char map[][];
	static Gidoong from, to;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		from = new Gidoong();  // BBB
		to = new Gidoong();  // EEE
		
		for(int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
			
			for(int c = 0; c < N; c++) {
				if(map[r][c] == '0' || map[r][c] == '1') continue;
				
				if(map[r][c] == 'B') from.point.add(new int[] {r, c});
				else if(map[r][c] == 'E') to.point.add(new int[] {r, c});
				map[r][c] = '0';	
			}
		}
		
		from.rowOrCol();
		to.rowOrCol();
		System.out.println(bfs());
	}

	// BFS로 기둥을 옮기는 최소 횟수 구하기
	private static int bfs() {
		boolean[][][] visited = new boolean[N][N][2];  // 기둥의 중심 + 방향으로 방문체크하는 3차원 배열
		Queue<Gidoong> queue = new ArrayDeque<>();
		queue.offer(from);
		visited[from.getMiddle()[0]][from.getMiddle()[1]][from.direction] = true;
		
		int level = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				Gidoong cur = queue.poll();
				
				for(int d = 0; d < 5; d++) {
					Gidoong next = getNext(cur, d);
					if(next == null) continue;
					if(visited[next.getMiddle()[0]][next.getMiddle()[1]][next.direction]) continue;
					
					if(next.getMiddle()[0] == to.getMiddle()[0] && next.getMiddle()[1] == to.getMiddle()[1]
							&& next.direction == to.direction) 
						return level + 1; 
					
					visited[next.getMiddle()[0]][next.getMiddle()[1]][next.direction] = true;
					queue.offer(next);
				}
			}
			
			level++;
		}
		
		return 0;
	}

	// 기둥이 움직일 수 있는 다음 지점 구하기
	private static Gidoong getNext(Gidoong cur, int d) {
		Gidoong next = new Gidoong();
		int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1};
		int[] dc = {0, 0, -1, 1, 1, -1, 1, -1};
		
		int[] first = new int[2], second = new int[2], third = new int[2];
		if(d < 4) {  // up, down, left, right
			first = new int[] {cur.point.get(0)[0] + dr[d], cur.point.get(0)[1] + dc[d]};
			second = new int[] {cur.point.get(1)[0] + dr[d], cur.point.get(1)[1] + dc[d]};
			third = new int[] {cur.point.get(2)[0] + dr[d], cur.point.get(2)[1] + dc[d]};
			
			if(first[0] < 0 || third[0] >= N || first[1] < 0 || third[1] >= N) 
				return null;  // 기둥이 범위를 벗어나면 null 반환
			if(map[first[0]][first[1]] == '1' || map[second[0]][second[1]]  == '1' || 
					map[third[0]][third[1]] == '1') 
				return null;  // 기둥이 이동할 범위에 다른 기둥이 있으면 null 반환
			
			next.point.add(first);
			next.point.add(second);
			next.point.add(third);
			next.direction = cur.direction;
		} else {  // turn
			int[] middle = cur.getMiddle();
			for(int i = 0; i < 8; i++) {  // 기둥의 중심을 기준으로 팔방에 대해 범위를 벗어나거나 다른 기둥이 있는지 여부를 확인
				int nr = middle[0] + dr[i];
				int nc = middle[1] + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1') 
					return null;
			}
			
			if(cur.direction == 0) {  // 현재 기둥이 행 방향인 경우 -> 열 방향으로 바꾸기
				next.point.add(new int[] {middle[0] - 1, middle[1]});
				next.point.add(middle);
				next.point.add(new int[] {middle[0] + 1, middle[1]});
				next.direction = 1;
			} else {  // 현재 기둥이 열 방향인 경우 -> 행 방향으로 바꾸기
				next.point.add(new int[] {middle[0], middle[1] - 1});
				next.point.add(middle);
				next.point.add(new int[] {middle[0], middle[1] + 1});
				next.direction = 0;
			}
		}
		
		return next;
	}
}
