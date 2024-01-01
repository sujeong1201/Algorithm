import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static char map[][];

    public static void main(String[] args) throws IOException {
        // ===== 입력 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int posR = -1, posC = -1;
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'I') {
                    posR = i;
                    posC = j;
                }
            }
        }
        // ===== 입력 끝

        bfs(posR, posC);
        System.out.println(answer == 0 ? "TT" : answer);
    }

    private static void bfs(int posR, int posC) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new int[] {posR, posC});
        visited[posR][posC] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M ||
                        visited[nr][nc] || map[nr][nc] == 'X') continue;

                if(map[nr][nc] == 'P') answer++;
                queue.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
}
