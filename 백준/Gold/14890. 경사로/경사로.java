import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    System.out.println(solve(N, L, map));
  }

  private static int solve(int n, int l, int[][] map) {
    //    boolean[][] runway = new boolean[n][n];
    int roadCnt = 0;

    // 열 탐색
    nextCol:
    for (int j = 0; j < n; j++) {
      int height = map[0][j];
      boolean[] runway = new boolean[n];
      for (int i = 1; i < n; i++) {
        int curHeight = map[i][j];
        if (height == curHeight) continue;
        if (Math.abs(height - curHeight) >= 2) continue nextCol;

        // 높이 차이가 1인 경우
        if (height < curHeight) {
          int end = i - l;
          if (end < 0) continue nextCol;
          for (int k = end; k < i; k++) {
            if (map[k][j] != height || runway[k]) continue nextCol;
          }
          for (int k = end; k < i; k++) runway[k] = true;
        } else {
          int end = i + l - 1;
          if (end >= n) continue nextCol;
          for (int k = i; k <= end; k++) {
            if (map[k][j] != curHeight || runway[k]) continue nextCol;
          }
          for (int k = i; k <= end; k++) runway[k] = true;
        }
        height = curHeight;
      }

      roadCnt++;
    }

    // 행 탐색
    nextRow:
    for (int i = 0; i < n; i++) {
      int height = map[i][0];
      boolean[] runway = new boolean[n];
      for (int j = 1; j < n; j++) {
        int curHeight = map[i][j];
        if (height == curHeight) continue;
        if (Math.abs(height - curHeight) >= 2) continue nextRow;

        // 높이 차이가 1인 경우
        if (height < curHeight) {
          int end = j - l;
          if (end < 0) continue nextRow;
          for (int k = end; k < j; k++) {
            if (map[i][k] != height || runway[k]) continue nextRow;
          }
          for (int k = end; k < j; k++) runway[k] = true;
        } else {
          int end = j + l - 1;
          if (end >= n) continue nextRow;
          for (int k = j; k <= end; k++) {
            if (map[i][k] != curHeight) continue nextRow;
          }
          for (int k = j; k <= end; k++) runway[k] = true;
        }
        height = curHeight;
      }

      roadCnt++;
    }

    return roadCnt;
  }
}
