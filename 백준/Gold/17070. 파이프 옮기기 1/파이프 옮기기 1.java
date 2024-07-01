import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, map[][], dp[][][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    dp = new int[N][N][3]; // 0-가로, 1-세로, 2-대각선
    if (map[0][2] == 0) dp[0][2][0] = 1;
    if (map[0][2] == 0 && map[1][1] == 0 && map[1][2] == 0) dp[1][2][2] = 1;

    System.out.println(solve(N - 1, N - 1, 0) + solve(N - 1, N - 1, 1) + solve(N - 1, N - 1, 2));
  }

  private static int solve(int r, int c, int dir) {
    if (map[r][c] == 1) return 0;

    if (dp[r][c][dir] == 0) {
      switch (dir) {
        case 0:
          if (c - 2 >= 0 && map[r][c - 2] == 0) dp[r][c][dir] += solve(r, c - 1, 0);
          if (c - 2 >= 0 && r - 1 >= 0 && map[r - 1][c - 2] == 0)
            dp[r][c][dir] += solve(r, c - 1, 2);
          break;
        case 1:
          if (r - 2 >= 0 && map[r - 2][c] == 0) dp[r][c][dir] += solve(r - 1, c, 1);
          if (r - 2 >= 0 && c - 1 >= 0 && map[r - 2][c - 1] == 0)
            dp[r][c][dir] += solve(r - 1, c, 2);
          break;
        case 2:
          if (r - 1 >= 0
              && c - 2 >= 0
              && map[r - 1][c - 2] == 0
              && map[r][c - 1] == 0
              && map[r - 1][c] == 0) dp[r][c][dir] += solve(r - 1, c - 1, 0);
          if (r - 2 >= 0
              && c - 1 >= 0
              && map[r - 2][c - 1] == 0
              && map[r][c - 1] == 0
              && map[r - 1][c] == 0) dp[r][c][dir] += solve(r - 1, c - 1, 1);
          if (r - 2 >= 0
              && c - 2 >= 0
              && map[r - 2][c - 2] == 0
              && map[r][c - 1] == 0
              && map[r - 1][c] == 0) dp[r][c][dir] += solve(r - 1, c - 1, 2);
          break;
      }
    }

    return dp[r][c][dir];
  }
}
