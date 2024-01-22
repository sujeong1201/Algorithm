import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, triangle[][], dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) triangle[i][j] = Integer.parseInt(st.nextToken());
            for(int j = i + 1; j < N; j++) triangle[i][j] = -1;
        }

        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        for(int c = 0; c < N; c++) solve(N - 1, c);
        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }

    public static int solve(int r, int c) {
        if(dp[r][c] == -1) {
            if (r == 0) {
                dp[r][c] = triangle[r][c];
            } else if(c == 0) {
                dp[r][c] = solve(r - 1, c) + triangle[r][c];
            } else if(c == r) {
                dp[r][c] = solve(r - 1, c - 1) + triangle[r][c];
            } else {
                dp[r][c] = Math.max(solve(r - 1, c), solve(r - 1, c - 1)) + triangle[r][c];
            }
        }

        return dp[r][c];
    }
}
