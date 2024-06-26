import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int R, C, N, map[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[R + 1][C + 1];

    N = Integer.parseInt(br.readLine());
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int dir = Integer.parseInt(st.nextToken());
      int length = Integer.parseInt(st.nextToken());
      int[] pos = getPosition(dir, length);
      map[pos[0]][pos[1]] = i;
    }

    st = new StringTokenizer(br.readLine());
    int[] dongguen =
        getPosition(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

    int[] distancesClock = getDistances(dongguen, true);
    int[] distancesCounter = getDistances(dongguen, false);
    int answer = 0;
    for (int i = 1; i <= N; i++) answer += Math.min(distancesClock[i], distancesCounter[i]);
    System.out.println(answer);
  }

  private static int[] getDistances(int[] dongguen, boolean clockwise) {
    int[] distances = new int[N + 1];
    int curR = dongguen[0];
    int curC = dongguen[1];

    int cnt = 0;
    do {
      cnt++;
      if (clockwise) {
        if (curR == 0 && curC >= 0 && curC < C) curC++;
        else if (curR == R && curC <= C && curC > 0) curC--;
        else if (curC == 0 && curR > 0 && curR <= R) curR--;
        else curR++;
      } else {
        if (curR == 0 && curC > 0 && curC <= C) curC--;
        else if (curR == R && curC >= 0 && curC < C) curC++;
        else if (curC == 0 && curR >= 0 && curR < R) curR++;
        else curR--;
      }

      if (map[curR][curC] > 0) distances[map[curR][curC]] = cnt;
    } while (curR != dongguen[0] || curC != dongguen[1]);

    if (map[curR][curC] > 0) distances[map[curR][curC]] = 0;

    return distances;
  }

  private static int[] getPosition(int dir, int length) {
    int[] pos = new int[2];

    switch (dir) {
      case 1:
        pos[0] = 0;
        pos[1] = length;
        break;
      case 2:
        pos[0] = R;
        pos[1] = length;
        break;
      case 3:
        pos[0] = length;
        pos[1] = 0;
        break;
      case 4:
        pos[0] = length;
        pos[1] = C;
        break;
    }

    return pos;
  }
}
