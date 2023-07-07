import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][];
    static int distance[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        distance = new int[N][M];
        for(int r = 0; r < N; r++) Arrays.fill(distance[r], -1);

        int destR = 0, destC = 0;
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2) {
                    destR = r;
                    destC = c;
                } else if(map[r][c] == 0) {
                    distance[r][c] = 0;
                }
            }
        }

        bfs(destR, destC);

        printDistance();
    }

    private static void printDistance() {
        StringBuilder sb = new StringBuilder();

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                sb.append(distance[r][c] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int destR, int destC) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        int level = 0;
        visited[destR][destC] = true;
        distance[destR][destC] = level;
        queue.offer(new int[] {destR, destC});

        while(!queue.isEmpty()) {
            ++level;
            int size = queue.size();

            for(int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if(visited[nr][nc] || map[nr][nc] == 0) continue;

                    visited[nr][nc] = true;
                    distance[nr][nc] = level;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
    }
}
