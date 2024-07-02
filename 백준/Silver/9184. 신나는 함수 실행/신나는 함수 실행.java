import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int dp[][][];

  public static void main(String[] args) throws IOException {
    dp = new int[21][21][21];
    dp[0][0][0] = 1;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      if (a == -1 && b == -1 && c == -1) break;

      bw.write("w(" + a + ", " + b + ", " + c + ") = " + getDp(a, b, c) + "\n");
    }

    br.close();
    bw.flush();
    bw.close();
  }

  private static int getDp(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      a = b = c = 0;
    }
    if (a > 20 || b > 20 || c > 20) {
      a = b = c = 20;
    }

    if (dp[a][b][c] == 0) {
      if (a < b && b < c) {
        dp[a][b][c] = getDp(a, b, c - 1) + getDp(a, b - 1, c - 1) - getDp(a, b - 1, c);
      } else {
        dp[a][b][c] =
            getDp(a - 1, b, c)
                + getDp(a - 1, b - 1, c)
                + getDp(a - 1, b, c - 1)
                - getDp(a - 1, b - 1, c - 1);
      }
    }

    return dp[a][b][c];
  }
}
