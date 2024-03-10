import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, lines[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new int[N][];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new int[] {a, b};
        }

        Arrays.sort(lines, (arr1, arr2) -> arr1[0] - arr2[0]);
        System.out.println(N - solve());
    }

    private static int solve() {
        int[] lis = new int[N];
        lis[0] = 1;

        for(int i = 1; i < N; i++) {
            lis[i] = 1;
            for(int j = 0; j < i; j++) {
                if(lines[j][1] < lines[i][1] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        return Arrays.stream(lis).max().getAsInt();
    }
}
