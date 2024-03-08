import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] < minHeight) minHeight = map[i][j];
                if(map[i][j] > maxHeight) maxHeight = map[i][j];
            }
        }

        System.out.println(solve(N, minHeight, maxHeight, map));
    }

    private static int solve(int N, int minHeight, int maxHeight, int[][] map) {
        int maxCnt = 1;

        for(int h = minHeight; h < maxHeight; h++) {
            boolean[][] safe = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] > h) safe[i][j] = true;
                }
            }

            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(safe[i][j]) {
                        bfs(N, safe, i, j);
                        cnt++;
                    }
                }
            }

            if(cnt > maxCnt) maxCnt = cnt;
        }

        return maxCnt;
    }

    private static void bfs(int N, boolean[][] safe, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        queue.offer(new int[] {startR, startC});
        safe[startR][startC] = false;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || !safe[nr][nc]) continue;

                queue.offer(new int[] {nr, nc});
                safe[nr][nc] = false;
            }
        }
    }
}
