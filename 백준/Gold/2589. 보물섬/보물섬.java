import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        // ===== 입력 끝

        int maxDistance = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'W') continue;

                int distance = bfs(i, j);
                if(distance > maxDistance) maxDistance = distance;
            }
        }

        System.out.println(maxDistance);
    }

    public static int bfs(int i, int j) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new int[] {i, j});
        visited[i][j] = true;

        int level = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M ||
                        map[nr][nc] == 'W' || visited[nr][nc]) continue;

                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            level++;
        }

        return level;
    }
}
