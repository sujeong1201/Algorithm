import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static class Fish {
    int no;
    int direction;
    int r;
    int c;
    boolean dead;

    public Fish(int no, int direction, int r, int c) {
      this.no = no;
      this.direction = direction;
      this.r = r;
      this.c = c;
      this.dead = false;
    }

    public Fish(Fish fish) {
      this.no = fish.no;
      this.direction = fish.direction;
      this.r = fish.r;
      this.c = fish.c;
      this.dead = fish.dead;
    }

    @Override
    public String toString() {
      return no + " " + direction + " " + r + " " + c;
    }
  }

  //  static int map[][], shark[];
  //  static Fish[] fishArr;
  static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] map = new int[4][4];
    Fish[] fishArr = new Fish[17];
    for (int i = 0; i < 4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken()) - 1;
        map[i][j] = a;
        fishArr[a] = new Fish(a, b, i, j);
      }
    }

    // 상어 (0, 0)에 위치
    int[] shark = new int[] {0, 0, fishArr[map[0][0]].direction};
    fishArr[map[0][0]].dead = true;
    int sum = map[0][0];
    map[0][0] = -1; // 상어 표시

    answer = 0;
    recur(sum, shark, map, fishArr);
    System.out.println(answer);
  }

  private static void recur(int sum, int[] shark, int[][] map, Fish[] fishArr) {
    int[][] mapClone = new int[4][];
    Fish[] fishArrClone = new Fish[17];
    for (int i = 0; i < 4; i++) mapClone[i] = map[i].clone();
    for (int i = 1; i <= 16; i++) fishArrClone[i] = new Fish(fishArr[i]);

    moveFish(mapClone, fishArrClone);

    int r = shark[0], c = shark[1], dir = shark[2];
    for (int i = 1; i <= 3; i++) {
      int nr = r + i * dr[dir];
      int nc = c + i * dc[dir];
      if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
        if (sum > answer) answer = sum;
        return;
      }
      if (mapClone[nr][nc] == 0) continue;

      int fish = mapClone[nr][nc];
      fishArrClone[fish].dead = true;
      mapClone[nr][nc] = -1;
      mapClone[r][c] = 0;
      shark[0] = nr;
      shark[1] = nc;
      shark[2] = fishArrClone[fish].direction;
      recur(sum + fish, shark, mapClone, fishArrClone);

      // 원상복귀
      fishArrClone[fish].dead = false;
      mapClone[nr][nc] = fish;
      mapClone[r][c] = -1;
      shark[0] = r;
      shark[1] = c;
      shark[2] = dir;
    }
  }

  private static void moveFish(int[][] map, Fish[] fishArr) {
    for (int i = 1; i <= 16; i++) {
      Fish cur = fishArr[i];
      if (cur.dead) continue;

      for (int d = 0; d < 8; d++) {
        int nr = cur.r + dr[(cur.direction + d) % 8];
        int nc = cur.c + dc[(cur.direction + d) % 8];
        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc] == -1) continue;

        if (map[nr][nc] != 0) {
          int change = map[nr][nc];
          fishArr[change].r = cur.r;
          fishArr[change].c = cur.c;
          map[cur.r][cur.c] = change;
        } else {
          map[cur.r][cur.c] = 0;
        }

        cur.direction = (cur.direction + d) % 8;
        cur.r = nr;
        cur.c = nc;
        map[nr][nc] = i;
        break;
      }
    }
  }
}
