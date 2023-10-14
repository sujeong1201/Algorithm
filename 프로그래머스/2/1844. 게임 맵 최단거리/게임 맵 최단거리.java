import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        int answer = bfs(N, M, maps);
        
        return answer;
    }
    
    public int bfs(int N, int M, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        boolean[][] visited = new boolean[N][M];
        
        int level = 1;
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                if(cur[0] == N - 1 && cur[1] == M - 1) return level;
                
                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if(visited[nr][nc] || map[nr][nc] == 0) continue;
                    
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            
            level++;
        }
        
        return -1;
    }
}