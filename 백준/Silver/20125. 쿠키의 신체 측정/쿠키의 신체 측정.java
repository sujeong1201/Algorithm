import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int N;
  static char map[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new char[N][N];
    for (int i = 0; i < N; i++) {
      map[i] = br.readLine().toCharArray();
    }

    int[] heart = findHeart();
    int[] bodyLength = getBodyLength(heart);
    System.out.println((heart[0] + 1) + " " + (heart[1] + 1));
    for (int i = 0; i < 5; i++) System.out.print(bodyLength[i] + " ");
  }

  private static int[] getBodyLength(int[] heart) {
    int[] bodyLength = new int[5];

    // 왼쪽 팔
    bodyLength[0] = getLength(heart[0], heart[1] - 1, 0)[0];
    // 오른쪽 팔
    bodyLength[1] = getLength(heart[0], heart[1] + 1, 1)[0];
    // 허리
    int[] result = getLength(heart[0] + 1, heart[1], 2);
    bodyLength[2] = result[0];
    // 왼쪽 다리
    bodyLength[3] = getLength(result[1], result[2] - 1, 3)[0];
    // 오른쪽 다리
    bodyLength[4] = getLength(result[1], result[2] + 1, 4)[0];

    return bodyLength;
  }

  private static int[] getLength(int r, int c, int type) {
    int length = 0;

    while (r >= 0 && r < N && c >= 0 && c < N) {
      if (map[r][c] == '_') break;
      length++;

      if (type == 0) c--;
      else if (type == 1) c++;
      else r++;
    }

    return new int[] {length, r, c};
  }

  private static int[] findHeart() {
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    for (int i = 0; i < N; i++) {
      outer:
      for (int j = 0; j < N; j++) {
        if (map[i][j] == '_') continue;

        for (int d = 0; d < 4; d++) {
          int nr = i + dir[d][0];
          int nc = j + dir[d][1];
          if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
          if (map[nr][nc] == '_') continue outer;
        }

        return new int[] {i, j};
      }
    }

    return null;
  }
}
