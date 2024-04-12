import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, map[][], answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    answer = 0;
    moveTornado();
    System.out.println(answer);
  }

  private static void moveTornado() {
    int[] dr = {0, 1, 0, -1};
    int[] dc = {-1, 0, 1, 0};

    int[] pos = {N / 2, N / 2};
    int direction = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(1);
    queue.offer(1);

    outer:
    while (pos[0] != 0 || pos[1] != 0) {
      int length = queue.poll();
      queue.offer(length + 1);

      for (int i = 0; i < length; i++) {
        int nr = pos[0] + dr[direction];
        int nc = pos[1] + dc[direction];
        int totalSand = 0;

        totalSand += 2 * (int) (map[nr][nc] * 0.01);
        flySand(
            (int) (map[nr][nc] * 0.01),
            pos[0] + dr[(direction + 1) % 4],
            pos[1] + dc[(direction + 1) % 4]);
        flySand(
            (int) (map[nr][nc] * 0.01),
            pos[0] + dr[(direction + 3) % 4],
            pos[1] + dc[(direction + 3) % 4]);

        totalSand += 2 * (int) (map[nr][nc] * 0.07);
        flySand(
            (int) (map[nr][nc] * 0.07), nr + dr[(direction + 1) % 4], nc + dc[(direction + 1) % 4]);
        flySand(
            (int) (map[nr][nc] * 0.07), nr + dr[(direction + 3) % 4], nc + dc[(direction + 3) % 4]);

        totalSand += 2 * (int) (map[nr][nc] * 0.02);
        flySand(
            (int) (map[nr][nc] * 0.02),
            nr + 2 * dr[(direction + 1) % 4],
            nc + 2 * dc[(direction + 1) % 4]);
        flySand(
            (int) (map[nr][nc] * 0.02),
            nr + 2 * dr[(direction + 3) % 4],
            nc + 2 * dc[(direction + 3) % 4]);

        totalSand += 2 * (int) (map[nr][nc] * 0.1);
        flySand(
            (int) (map[nr][nc] * 0.1),
            nr + dr[direction] + dr[(direction + 1) % 4],
            nc + dc[direction] + dc[(direction + 1) % 4]);
        flySand(
            (int) (map[nr][nc] * 0.1),
            nr + dr[direction] + dr[(direction + 3) % 4],
            nc + dc[direction] + dc[(direction + 3) % 4]);

        totalSand += (int) (map[nr][nc] * 0.05);
        flySand((int) (map[nr][nc] * 0.05), nr + 2 * dr[direction], nc + 2 * dc[direction]);

        flySand(map[nr][nc] - totalSand, nr + dr[direction], nc + dc[direction]);

        map[nr][nc] = 0;
        pos[0] = nr;
        pos[1] = nc;

        if (pos[0] == 0 && pos[1] == 0) break outer;
      }

      direction = (direction + 1) % 4;
    }
  }

  private static void flySand(int sand, int r, int c) {
    if (r >= 0 && r < N && c >= 0 && c < N) {
      map[r][c] += sand;
    } else {
      answer += sand;
    }
  }
}
