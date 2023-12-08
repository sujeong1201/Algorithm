import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[M][N];

        for(int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        if(bfs()) System.out.println("YES");
        else System.out.println("NO");
    }

    public static boolean bfs() {
        boolean[][] visited = new boolean[M][N];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        for(int i = 0; i < N; i++) {
            if(map[0][i] == '0') {
                queue.offer(new int[] {0, i});
                visited[0][i] = true;
            }
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];
                if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                if(nr == M - 1) return true;

                if(!visited[nr][nc] && map[nr][nc] == '0') {
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }
}
