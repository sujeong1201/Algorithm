import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int L = Integer.parseInt(st.nextToken());
      int R = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      if (L == 0) break;

      int[] startP = new int[3];
      char[][][] map = new char[L][R][C];
      for (int i = 0; i < L; i++) {
        for (int j = 0; j < R; j++) {
          map[i][j] = br.readLine().toCharArray();
          for (int k = 0; k < C; k++) {
            if (map[i][j][k] == 'S') startP = new int[] {i, j, k};
          }
        }
        br.readLine();
      }

      int result = bfs(L, R, C, map, startP);
      bw.write(result == -1 ? "Trapped!\n" : "Escaped in " + result + " minute(s).\n");
    }

    br.close();
    bw.flush();
    bw.close();
  }

  private static int bfs(int L, int R, int C, char[][][] map, int[] startP) {
    Queue<int[]> queue = new ArrayDeque<>();
    boolean[][][] visited = new boolean[L][R][C];
    int[][] dir = {{0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};

    queue.offer(startP);
    visited[startP[0]][startP[1]][startP[2]] = true;

    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int s = 0; s < size; s++) {
        int[] cur = queue.poll();

        for (int d = 0; d < 6; d++) {
          int nl = cur[0] + dir[d][0];
          int nr = cur[1] + dir[d][1];
          int nc = cur[2] + dir[d][2];
          if (nl < 0
              || nl >= L
              || nr < 0
              || nr >= R
              || nc < 0
              || nc >= C
              || visited[nl][nr][nc]
              || map[nl][nr][nc] == '#') continue;
          if (map[nl][nr][nc] == 'E') return level + 1;

          queue.offer(new int[] {nl, nr, nc});
          visited[nl][nr][nc] = true;
        }
      }

      level++;
    }

    return -1;
  }
}
