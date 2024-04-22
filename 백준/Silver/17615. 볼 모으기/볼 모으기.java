import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int N, minMove;
  static char balls[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    balls = br.readLine().toCharArray();

    minMove = Integer.MAX_VALUE;
    // 4가지 경우 중 최소값 - 빨강 맨뒤로, 빨강 맨앞으로, 파랑 맨뒤로, 파랑 맨앞으로
    // 1. 빨강 맨뒤로
    gatherRear('R');
    // 2. 빨강 맨앞으로
    gatherFront('R');
    // 3. 파랑 맨뒤로
    gatherRear('B');
    // 4. 파랑 맨앞으로
    gatherFront('B');

    System.out.println(minMove);
  }

  public static void gatherFront(char color) {
    boolean first = false;
    int moveCnt = 0;
    char otherColor = color == 'R' ? 'B' : 'R';
    for (int i = 0; i < N; i++) {
      if (balls[i] == otherColor && !first) first = true;
      if (balls[i] == color && first) moveCnt++;
    }

    if (moveCnt < minMove) minMove = moveCnt;
  }

  public static void gatherRear(char color) {
    boolean first = false;
    int moveCnt = 0;
    char otherColor = color == 'R' ? 'B' : 'R';
    for (int i = N - 1; i >= 0; i--) {
      if (balls[i] == otherColor && !first) first = true;
      if (balls[i] == color && first) moveCnt++;
    }

    if (moveCnt < minMove) minMove = moveCnt;
  }
}
