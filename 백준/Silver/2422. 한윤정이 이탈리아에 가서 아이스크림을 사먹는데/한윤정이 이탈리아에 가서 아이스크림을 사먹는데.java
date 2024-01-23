import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean badComb[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        badComb = new boolean[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int no1 = Integer.parseInt(st.nextToken());
            int no2 = Integer.parseInt(st.nextToken());
            badComb[no1][no2] = badComb[no2][no1] = true;
        }

        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                for(int k = j + 1; k <= N; k++) {
                    if(badComb[i][j] || badComb[j][k] || badComb[i][k]) continue;

                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
