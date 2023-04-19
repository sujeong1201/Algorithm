import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int N, puzzleNum, maxFill, gBoard[][], pBoard[][];
	static List<int[]> blank, puzzle;
	static int delta[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int solution(int[][] game_board, int[][] table) {
        gBoard = game_board;
    	pBoard = table;
    	blank = new ArrayList<>();
    	puzzle = new ArrayList<>();
    	N = game_board.length;
    	boolean[][] visited = new boolean[N][N];
    	
    	for(int r = 0; r < N; r++) {
    		for(int c = 0; c < N; c++) {
    			if(game_board[r][c] == 0 && !visited[r][c]) {
    				blank.clear();
    				bfs(r, c, game_board, blank, game_board[r][c], visited);
    				for(int i = 0; i < 4; i++) {
    					// 맞는 퍼즐 있는지 확인하고 퍼즐판 돌리기
    					if(findPuzzle()) break;
    					rotate();
    				}
    			}
    		}
    	}
    	
        return maxFill;
    }
    
    private static void rotate() {
		int[][] boardCopy = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				boardCopy[c][N - r - 1] = pBoard[r][c];
			}
		}
		pBoard = boardCopy;
	}

	private static boolean findPuzzle() {
		boolean[][] visited = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!visited[r][c] && pBoard[r][c] == 1) {
					puzzle.clear();
					bfs(r, c, pBoard, puzzle, pBoard[r][c], visited);
					// 빈칸(blank)과 퍼즐(puzzle) 패턴이 같은지 확인하기
					if(ableToPut()) return true;
				}
			}
		}
		
		return false;
	}

	private static boolean ableToPut() {
		if(blank.size() != puzzle.size()) return false;
		
		int[] firstB = blank.get(0);
		int[] firstP = puzzle.get(0);
		int idx = 1;
		for( ; idx < blank.size(); idx++) {
			if(idx >= puzzle.size()) break;
			
			int[] b = blank.get(idx);
			int[] p = puzzle.get(idx);
			if(b[0] - firstB[0] != p[0] - firstP[0] || b[1] - firstB[1] != p[1] - firstP[1])
				break;
		}
		
		if(idx == blank.size()) {
			maxFill += blank.size();
			for(int[] cur : blank) {
				gBoard[cur[0]][cur[1]] = 1;
			}
			
			for(int[] cur : puzzle) {
				pBoard[cur[0]][cur[1]] = 0;
			}
			
			return true;
		}
		
		return false;
	}

	private static void bfs(int r, int c, int[][] board, List<int[]> list, int num, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		list.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + delta[d][0];
				int nc = cur[1] + delta[d][1];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(!visited[nr][nc] && board[nr][nc] == num) {
					queue.offer(new int[] {nr, nc});
					list.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
}