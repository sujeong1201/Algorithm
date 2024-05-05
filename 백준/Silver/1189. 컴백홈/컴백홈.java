import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int R, C, K, answer;
  static char[][] map;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    map = new char[R][C];
    for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

    visited = new boolean[R][C];
    answer = 0;
    dfs(R - 1, 0, 1);
    System.out.println(answer);
  }

  private static void dfs(int r, int c, int cnt) {
    if (cnt == K) {
      if (r == 0 && c == C - 1) answer++;
      return;
    }

    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};

    visited[r][c] = true;
    for (int d = 0; d < 4; d++) {
      int nr = r + dr[d];
      int nc = c + dc[d];
      if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[r][c] == 'T') continue;

      dfs(nr, nc, cnt + 1);
    }
    visited[r][c] = false;
  }
}
