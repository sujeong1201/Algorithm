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
      // 1. 외부 공간 표시
      bfs();
      // 2. 치즈 가장자리 녹기
      meltCheese();
      // 3. 치즈가 다 녹았는지 확인하기
      if (isFinished()) break;
    }

    System.out.println(time);
  }

  private static boolean isFinished() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1) return false;
      }
    }

    return true;
  }

  private static void meltCheese() {
    boolean[][] isMelted = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1) {
          int cnt = 0;
          for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == -1) cnt++;
          }

          if (cnt >= 2) isMelted[i][j] = true;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (isMelted[i][j]) {
          map[i][j] = 0;
        }
      }
    }
  }

  private static void bfs() {
    Queue<int[]> queue = new ArrayDeque<>();
    boolean[][] visited = new boolean[N][M];

    queue.offer(new int[] {0, 0});
    visited[0][0] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      map[cur[0]][cur[1]] = -1;

      for (int d = 0; d < 4; d++) {
        int nr = cur[0] + dr[d];
        int nc = cur[1] + dc[d];
        if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) continue;

        queue.offer(new int[] {nr, nc});
        visited[nr][nc] = true;
      }
    }
  }
}
