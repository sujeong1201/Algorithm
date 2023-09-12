import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, K;
    static int map[][], cnt;
    static boolean visited[][];
    static List<Integer> sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        
        cnt = 1;
        visited = new boolean[M][N];
        sizes = new ArrayList<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int leftBottomC = Integer.parseInt(st.nextToken());
            int leftBottomR = M - Integer.parseInt(st.nextToken());
            int rightTopC = Integer.parseInt(st.nextToken());
            int rightTopR = M - Integer.parseInt(st.nextToken());

            for(int r = rightTopR; r < leftBottomR; r++) {
                for(int c = leftBottomC; c < rightTopC; c++) {
                   map[r][c] = -1;
                }
            }
        }

        for(int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c] == 0) {
                    bfs(r, c);
                }
            }
        }

        Collections.sort(sizes);
        StringBuilder sb = new StringBuilder();
        for(Integer size : sizes) sb.append(size + " ");
        System.out.println(cnt - 1);
        System.out.println(sb.toString());
    }

    public static void bfs(int r, int c) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        Queue<int[]> queue = new ArrayDeque<>();
        int size = 0;

        visited[r][c] = true;
        queue.offer(new int[] {r, c});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            map[current[0]][current[1]] = cnt;
            size++;

            for(int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];
                if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc]) continue;

                if(map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }

        cnt++;
        sizes.add(size);
    }
}
