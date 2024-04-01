import java.util.Scanner;

public class Main {
  static int N, K, P, X;
  static boolean led[][];
  static int cntForChange[][];
  static int answer;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt(); // 최대 층수
    K = sc.nextInt(); // 디스플레이 자리수
    P = sc.nextInt(); // 반전시킬 수 있는 LED 최대 수
    X = sc.nextInt(); // 실제 층

    answer = 0;
    solve();
    System.out.println(answer);
  }

  public static void solve() {
    led =
        new boolean[][] {
          {true, true, true, false, true, true, true},
          {false, false, true, false, false, true, false},
          {true, false, true, true, true, false, true},
          {true, false, true, true, false, true, true},
          {false, true, true, true, false, true, false},
          {true, true, false, true, false, true, true},
          {true, true, false, true, true, true, true},
          {true, false, true, false, false, true, false},
          {true, true, true, true, true, true, true},
          {true, true, true, true, false, true, true}
        };
    cntForChange = new int[10][10];

    int[] floor = new int[K];
    int x = X;
    int index = K - 1;
    while (x > 0) {
      floor[index--] = x % 10;
      x /= 10;
    }
    int maxDigit = (int) (Math.log10(N) + 1);

    recur(K - maxDigit < 0 ? 0 : K - maxDigit, P, floor);
  }

  private static void recur(int index, int remainedCnt, int[] floor) {
    if (index == K) {
      int floorInt = toInteger(floor);
      if (floorInt != X && floorInt >= 1 && floorInt <= N) {
        answer++;
      }

      return;
    }

    int curNum = floor[index];
    for (int i = 0; i <= 9; i++) {
      if (i == curNum) continue;
      int change = getCntForChange(curNum, i);
      if (remainedCnt >= change) {
        floor[index] = i;
        recur(index + 1, remainedCnt - change, floor);
        floor[index] = curNum;
      }
    }

    recur(index + 1, remainedCnt, floor);
  }

  private static int toInteger(int[] floor) {
    int result = 0;
    for (int i = 0; i < K; i++) {
      result += (int) (floor[i] * Math.pow(10, K - i - 1));
    }

    return result;
  }

  public static int getCntForChange(int from, int to) {
    if (cntForChange[from][to] == 0) {
      int cnt = 0;
      for (int i = 0; i < 7; i++) {
        if (led[from][i] != led[to][i]) cnt++;
      }

      cntForChange[from][to] = cntForChange[to][from] = cnt;
    }

    return cntForChange[from][to];
  }
}
