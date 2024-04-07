import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, map[][];
  static boolean cloud[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    cloud = new boolean[N][N];
    cloud[N - 1][0] = cloud[N - 1][1] = cloud[N - 2][0] = cloud[N - 2][1] = true;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      moveCloud(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        answer += map[i][j];
      }
    }
    System.out.println(answer);
  }

  private static void moveCloud(int d, int s) {
    // 1. 모든 구름은 d방향으로 s칸 이동
    boolean[][] newCloud = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!cloud[i][j]) continue;

        int r = i, c = j;
        switch (d) {
          case 1: // 왼쪽
            c = calculate(c, s, false);
            break;
          case 2: // 왼쪽위
            r = calculate(r, s, false);
            c = calculate(c, s, false);
            break;
          case 3: // 위
            r = calculate(r, s, false);
            break;
          case 4: // 오른쪽위
            r = calculate(r, s, false);
            c = calculate(c, s, true);
            break;
          case 5: // 오른쪽
            c = calculate(c, s, true);
            break;
          case 6: // 오른쪽아래
            r = calculate(r, s, true);
            c = calculate(c, s, true);
            break;
          case 7: // 아래
            r = calculate(r, s, true);
            break;
          case 8: // 왼쪽아래
            r = calculate(r, s, true);
            c = calculate(c, s, false);
            break;
        }
        newCloud[r][c] = true;
        // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
        map[r][c]++;
      }
    }

    // 4. 물복사버그 마법 - 대각선 방향 거리 1인 칸에 물이 있는 바구니 수만큼 바구니의 물 양 증가
    int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, 1, -1};
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!newCloud[i][j]) continue;

        int cnt = 0;
        for (int k = 0; k < 4; k++) {
          int nr = i + dr[k];
          int nc = j + dc[k];
          if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
          if (map[nr][nc] > 0) cnt++;
        }

        map[i][j] += cnt;
      }
    }

    // 5. 물의 양이 2 이상인 모든 칸에 구름 생김, 이전에 구름이 있던 자리는 안됨
    cloud = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] < 2 || newCloud[i][j]) continue;

        map[i][j] -= 2;
        cloud[i][j] = true;
      }
    }
  }

  public static int calculate(int num, int s, boolean isPlus) {
    if (isPlus) {
      num += s;
      while (num >= N) num -= N;
    } else {
      num -= s;
      while (num < 0) num += N;
    }

    return num;
  }
}
