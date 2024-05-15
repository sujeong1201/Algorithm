import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, map[][];
  static int[] dr = {0, 0, 1, -1};
  static int[] dc = {1, -1, 0, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    int time = 0;
    while (true) {
      time++;
      melt();

      if (isSeparate()) break;
      if (isAllMelted()) {
        time = 0;
        break;
      }
    }

    System.out.println(time);
  }

  private static boolean isAllMelted() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] > 0) return false;
      }
    }

    return true;
  }

  private static boolean isSeparate() {
    // bfs
    Queue<int[]> queue = new ArrayDeque<>();
    boolean[][] visited = new boolean[N][M];

    outer:
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] > 0) {
          queue.offer(new int[] {i, j});
          visited[i][j] = true;
          break outer;
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();

      for (int d = 0; d < 4; d++) {
        int nr = cur[0] + dr[d];
        int nc = cur[1] + dc[d];
        if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;

        queue.offer(new int[] {nr, nc});
        visited[nr][nc] = true;
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visited[i][j] && map[i][j] > 0) return true;
      }
    }

    return false;
  }

  private static void melt() {
    int[][] newMap = new int[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 0) continue;

        int cnt = 0;
        for (int d = 0; d < 4; d++) {
          int nr = i + dr[d];
          int nc = j + dc[d];
          if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
          if (map[nr][nc] == 0) cnt++;
        }

        newMap[i][j] = map[i][j] - cnt < 0 ? 0 : map[i][j] - cnt;
      }
    }

    map = newMap;
  }
}
