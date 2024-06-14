import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int diceSideInfo[][], diceTopInfo[];
  static int N, dices[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    dices = new int[N][6];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 6; j++) dices[i][j] = Integer.parseInt(st.nextToken());
    }

    // 0-5, 1-3, 2-4가 짝임
    diceSideInfo = new int[6][4]; // 각 면이 바닥일 때 옆면들의 인덱스
    diceSideInfo[0] = diceSideInfo[5] = new int[] {1, 2, 3, 4};
    diceSideInfo[1] = diceSideInfo[3] = new int[] {0, 2, 4, 5};
    diceSideInfo[2] = diceSideInfo[4] = new int[] {0, 1, 3, 5};
    diceTopInfo = new int[] {5, 3, 4, 1, 2, 0};

    int maxSum = 0;
    // 1번 주사위의 윗면이 각 면인 경우에 대해 반복
    for (int i = 0; i < 6; i++) {
      int sum = solve(i);
      if (sum > maxSum) maxSum = sum;
    }

    System.out.println(maxSum);
  }

  private static int solve(int firstTopIdx) {
    int nextBottom = dices[0][firstTopIdx];
    int sideSum =
        Arrays.stream(diceSideInfo[firstTopIdx]).map((side) -> dices[0][side]).max().getAsInt();

    for (int i = 1; i < N; i++) {
      int nextTopIdx = -1;
      for (int j = 0; j < 6; j++) if (dices[i][j] == nextBottom) nextTopIdx = j;
      nextBottom = dices[i][diceTopInfo[nextTopIdx]];

      int[] dice = dices[i];
      sideSum += Arrays.stream(diceSideInfo[nextTopIdx]).map((side) -> dice[side]).max().getAsInt();
    }

    return sideSum;
  }
}
