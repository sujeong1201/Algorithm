import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static class Dice {
    int top;
    int bottom;
    int left;
    int right;
    int front;
    int back;
  }

  static int N, M, map[][], x, y;
  static Dice dice;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
    }

    dice = new Dice();
    st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < K; i++) {
      int direction = Integer.parseInt(st.nextToken());
      if (!throwDice(direction)) continue;
      sb.append(dice.top + "\n");
    }

    System.out.print(sb);
  }

  private static boolean throwDice(int direction) {
    switch (direction) {
      case 1: // 동
        if (y + 1 >= M) return false;
        y++;
        int temp = dice.left;
        dice.left = dice.bottom;
        dice.bottom = dice.right;
        dice.right = dice.top;
        dice.top = temp;
        break;
      case 2: // 서
        if (y - 1 < 0) return false;
        y--;
        temp = dice.left;
        dice.left = dice.top;
        dice.top = dice.right;
        dice.right = dice.bottom;
        dice.bottom = temp;
        break;
      case 3: // 북
        if (x - 1 < 0) return false;
        x--;
        temp = dice.top;
        dice.top = dice.front;
        dice.front = dice.bottom;
        dice.bottom = dice.back;
        dice.back = temp;
        break;
      case 4: // 남
        if (x + 1 >= N) return false;
        x++;
        temp = dice.top;
        dice.top = dice.back;
        dice.back = dice.bottom;
        dice.bottom = dice.front;
        dice.front = temp;
        break;
    }

    if (map[x][y] == 0) {
      map[x][y] = dice.bottom;
    } else {
      dice.bottom = map[x][y];
      map[x][y] = 0;
    }
    return true;
  }
}
