import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, population[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    population = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) population[i][j] = Integer.parseInt(st.nextToken());
    }

    System.out.println(solve());
  }

  private static int solve() {
    int minPopulationDiff = Integer.MAX_VALUE;

    // x, y, d1, d2 모든 조합 탐색 -> N^4 (최대 160,000)
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < N; y++) {
        for (int d1 = 1; d1 < N - 1; d1++) {
          for (int d2 = 1; d2 < N - 1; d2++) {
            if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) continue;

            boolean[][] five = new boolean[N][N];
            List<Integer> populationSum = new ArrayList<>();

            // 5번 선거구
            int sum = 0;
            for (int r = x; r <= x + d1 + d2; r++) {
              int diff = r - x;
              int cStart = diff <= d1 ? y - diff : y + diff - 2 * d1;
              int cEnd = diff <= d2 ? y + diff : y - diff + 2 * d2;
              for (int c = cStart; c <= cEnd; c++) {
                five[r][c] = true;
                sum += population[r][c];
              }
            }
            populationSum.add(sum);
            // 1번 선거구
            populationSum.add(getPopulationSum(0, x + d1 - 1, 0, y, five));
            // 2번 선거구
            populationSum.add(getPopulationSum(0, x + d2, y + 1, N - 1, five));
            // 3번 선거구
            populationSum.add(getPopulationSum(x + d1, N - 1, 0, y - d1 + d2 - 1, five));
            // 4번 선거구
            populationSum.add(getPopulationSum(x + d2 + 1, N - 1, y - d1 + d2, N - 1, five));

            int populationDiff =
                populationSum.stream().max(Integer::compareTo).orElseThrow()
                    - populationSum.stream().min(Integer::compareTo).orElseThrow();
            if (populationDiff < minPopulationDiff) minPopulationDiff = populationDiff;
          }
        }
      }
    }

    return minPopulationDiff;
  }

  public static int getPopulationSum(int rStart, int rEnd, int cStart, int cEnd, boolean[][] five) {
    int sum = 0;
    for (int r = rStart; r <= rEnd; r++) {
      for (int c = cStart; c <= cEnd; c++) {
        if (!five[r][c]) sum += population[r][c];
      }
    }

    return sum;
  }
}
