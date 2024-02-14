import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] dpMax = new int[N][3];
        int[][] dpMin = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dpMax[0] = map[0].clone();
        for(int i = 1; i < N; i++) Arrays.fill(dpMax[i], -1);
        for(int i = 0; i < 3; i++) getMinOrMax(true, N - 1, i, map, dpMax);

        dpMin[0] = map[0].clone();
        for(int i = 1; i < N; i++) Arrays.fill(dpMin[i], -1);
        for(int i = 0; i < 3; i++) getMinOrMax(false, N - 1, i, map, dpMin);

        System.out.println(Arrays.stream(dpMax[N - 1]).max().getAsInt() + " " +
                Arrays.stream(dpMin[N - 1]).min().getAsInt());
    }

    private static int getMinOrMax(boolean minOrMax, int r, int c, int[][] map, int[][] dp) {
        if(c == -1) return minOrMax ? 0 : Integer.MAX_VALUE;
        if(c == 3) return minOrMax ? 0 : Integer.MAX_VALUE;

        if(dp[r][c] == -1) {
            int left = getMinOrMax(minOrMax, r - 1, c - 1, map, dp);
            int up = getMinOrMax(minOrMax, r - 1, c, map, dp);
            int right = getMinOrMax(minOrMax, r - 1, c + 1, map, dp);

            if(minOrMax) {  // 최대값을 구하는 경우
                int temp = left > up ? left : up;
                int prevMax = right > temp ? right : temp;
                dp[r][c] = prevMax + map[r][c];
            } else {  // 최소값을 구하는 경우
                int temp = left < up ? left : up;
                int prevMin = right < temp ? right : temp;
                dp[r][c] = prevMin + map[r][c];
            }
        }

        return dp[r][c];
    }

}
