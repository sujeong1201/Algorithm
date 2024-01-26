import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N];
            for(int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) stickers[i][j] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[2][N];
            for(int i = 0; i < 2; i++) Arrays.fill(dp[i], -1);

            int max = Math.max(solve(0, N - 1, N, stickers, dp),
                                solve(1, N - 1, N, stickers, dp));
            sb.append(max + "\n");
        }

        System.out.println(sb);
    }

    private static int solve(int r, int c, int n, int[][] stickers, int[][] dp) {
        if(c < 0 || c >= n) return 0;
        if(dp[r][c] == -1) {
            int otherR = r == 0 ? 1 : 0;
            dp[r][c] = stickers[r][c] +
                    Math.max(solve(otherR, c - 1, n, stickers, dp), solve(otherR, c - 2, n, stickers, dp));
        }

        return dp[r][c];
    }
}
