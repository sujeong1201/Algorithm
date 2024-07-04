import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int K, sum[][], dp[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      K = Integer.parseInt(br.readLine());
      int[] pages = new int[K];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < K; i++) pages[i] = Integer.parseInt(st.nextToken());

      dp = new int[K][K]; // i~j 합치는 데 필요한 최소 비용
      sum = new int[K][K]; // i~j 파일 크기의 합
      for (int i = 0; i < K; i++) {
        for (int j = i; j < K; j++) {
          if (j == 0) sum[i][j] = pages[j];
          else sum[i][j] = sum[i][j - 1] + pages[j];
        }
      }

      for (int i = 0; i < K; i++) {
        Arrays.fill(dp[i], -1);
        dp[i][i] = 0;
      }

      bw.write(getDp(0, K - 1) + "\n");
    }

    bw.flush();
    bw.close();
  }

  private static int getDp(int start, int end) {
    if (dp[start][end] == -1) {
      int min = Integer.MAX_VALUE;
      for (int i = start; i < end; i++) {
        int cur = getDp(start, i) + getDp(i + 1, end) + sum[start][end];
        if (cur < min) min = cur;
      }

      dp[start][end] = min;
    }

    return dp[start][end];
  }
}
