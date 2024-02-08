import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, S, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        bfs();
        System.out.println(map[X][Y]);
    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]);  // {바이러스종류, r, c}
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) pq.offer(new int[] {map[i][j], i, j});
            }
        }

        int level = 0;
        while(level < S && !pq.isEmpty()) {
            int size = pq.size();
            Queue<int[]> tempQue = new ArrayDeque<>();

            for(int s = 0; s < size; s++) {
                int[] cur = pq.poll();
                for(int d = 0; d < 4; d++) {
                    int nr = cur[1] + dr[d];
                    int nc = cur[2] + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if(map[nr][nc] > 0) continue;

                    map[nr][nc] = cur[0];
                    tempQue.offer(new int[] {map[nr][nc], nr, nc});
                }
            }

            int tempQueSize = tempQue.size();
            for(int i = 0; i < tempQueSize; i++) pq.offer(tempQue.poll());
            level++;
        }
    }
}
