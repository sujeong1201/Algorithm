import java.io.*;
import java.util.*;

public class Main {
  static class Robot {
    int r;
    int c;
    int d; // 0-북, 1-동, 2-남, 3-서

    public Robot(int r, int c, int d) {
      this.r = r;
      this.c = c;
      this.d = d;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    Robot initial =
        new Robot(
            Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken()));
    int[][] map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    System.out.println(bfs(initial, map, N, M));
  }

  public static int bfs(Robot initial, int[][] map, int N, int M) {
    Robot current = initial;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1}; // 북, 동, 남, 서 순서

    int cnt = 0;
    outer:
    while (true) {
      if (map[current.r][current.c] == 0) {
        map[current.r][current.c] = -1;
        cnt++;
      }

      for (int d = 1; d <= 4; d++) {
        // 현재 방향 기준 반시계 순서로 청소되지 않은 빈 칸인지 확인
        int nextDir = current.d - d;
        if (nextDir < 0) nextDir += 4;
        int nr = current.r + dr[nextDir];
        int nc = current.c + dc[nextDir];
        if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

        if (map[nr][nc] == 0) {
          current = new Robot(nr, nc, nextDir);
          continue outer;
        }
      }

      // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
      int back = (current.d + 2) % 4;
      int nr = current.r + dr[back];
      int nc = current.c + dc[back];
      if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1) break;
      current = new Robot(nr, nc, current.d);
    }

    return cnt;
  }
}
