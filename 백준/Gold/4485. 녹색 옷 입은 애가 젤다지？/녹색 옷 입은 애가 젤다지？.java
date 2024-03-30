import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int t = 1; ; t++) {
      int N = Integer.parseInt(br.readLine());
      if (N == 0) break;

      int[][] map = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int result = bfs(N, map);
      sb.append("Problem " + t + ": " + result + "\n");
    }

    System.out.println(sb);
  }

  private static int bfs(int N, int[][] map) {
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};
    PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[2] - arr2[2]);
    boolean[][] visited = new boolean[N][N];

    pq.offer(new int[] {0, 0, map[0][0]});
    visited[0][0] = true;

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();

      for (int d = 0; d < 4; d++) {
        int nr = cur[0] + dr[d];
        int nc = cur[1] + dc[d];
        if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

        if (nr == N - 1 && nc == N - 1) return cur[2] + map[nr][nc];
        pq.offer(new int[] {nr, nc, cur[2] + map[nr][nc]});
        visited[nr][nc] = true;
      }
    }

    return -1;
  }
}
