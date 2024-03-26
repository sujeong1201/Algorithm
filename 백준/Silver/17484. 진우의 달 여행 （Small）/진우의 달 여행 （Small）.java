import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static class Move {
    int r;
    int c;
    int weight;
    int direction; // 0-왼쪽아래, 1-아래, 2-오른쪽아래

    public Move(int r, int c, int weight, int direction) {
      this.r = r;
      this.c = c;
      this.weight = weight;
      this.direction = direction;
    }
  }

  static int N, M, map[][];

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

    bfs(map);
  }

  public static void bfs(int[][] map) {
    PriorityQueue<Move> queue = new PriorityQueue<>((m1, m2) -> m1.weight - m2.weight);
    int[] dc = {-1, 0, 1};

    for (int j = 0; j < M; j++) {
      queue.offer(new Move(0, j, map[0][j], -1));
    }

    int min = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      Move cur = queue.poll();
      if (cur.r == N - 1) {
        if (cur.weight < min) min = cur.weight;
        continue;
      }

      for (int d = 0; d < 3; d++) {
        if (cur.direction == d) continue;

        int nr = cur.r + 1;
        int nc = cur.c + dc[d];
        if (nc < 0 || nc >= M) continue;

        queue.offer(new Move(nr, nc, cur.weight + map[nr][nc], d));
      }
    }

    System.out.println(min);
  }
}
