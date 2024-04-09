import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static class FireBall {
    int no;
    int mass; // 질량
    int direction; // 방향
    int speed; // 속력

    public FireBall(int no, int mass, int speed, int direction) {
      this.no = no;
      this.mass = mass;
      this.speed = speed;
      this.direction = direction;
    }
  }

  static int N;
  static Queue<FireBall>[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    map = new ArrayDeque[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = new ArrayDeque<>();
      }
    }

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      map[r][c].add(
          new FireBall(
              0,
              Integer.parseInt(st.nextToken()),
              Integer.parseInt(st.nextToken()),
              Integer.parseInt(st.nextToken())));
    }

    for (int i = 1; i <= K; i++) {
      doMoveCommand(i);
    }

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        while (!map[i][j].isEmpty()) {
          answer += map[i][j].poll().mass;
        }
      }
    }
    System.out.println(answer);
  }

  private static void doMoveCommand(int no) {
    int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    // 1. 모든 파이어볼 이동
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        while (!map[i][j].isEmpty()) {
          FireBall cur = map[i][j].poll();
          if (cur.no == no) {
            map[i][j].offer(cur);
            break;
          }

          int nr = i + dr[cur.direction] * cur.speed;
          while (nr < 0) nr += N;
          nr %= N;
          int nc = j + dc[cur.direction] * cur.speed;
          while (nc < 0) nc += N;
          nc %= N;
          cur.no = no;
          map[nr][nc].offer(cur);
        }
      }
    }

    // 2-1. 같은 칸 파이어볼을 모두 하나로 합친다.
    // 2-2. 파이어볼을 4개로 나눈다.
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        Queue<FireBall> curQueue = map[i][j];
        int size = curQueue.size();
        if (size <= 1) continue;

        int totalMass = 0;
        int totalSpeed = 0;
        int evenCnt = 0;
        int oddCnt = 0;
        while (!curQueue.isEmpty()) {
          FireBall cur = curQueue.poll();
          totalMass += cur.mass;
          totalSpeed += cur.speed;
          if (cur.direction % 2 == 0) oddCnt++;
          else evenCnt++;
        }

        if (totalMass / 5 == 0) continue;
        int[] newDirection = new int[4];
        if (evenCnt == 0 || oddCnt == 0) newDirection = new int[] {0, 2, 4, 6};
        else newDirection = new int[] {1, 3, 5, 7};
        for (int k = 0; k < 4; k++) {
          curQueue.offer(new FireBall(no, totalMass / 5, totalSpeed / size, newDirection[k]));
        }
      }
    }
  }
}
