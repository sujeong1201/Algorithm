import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int level[], mistake[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        level = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) level[i] = Integer.parseInt(st.nextToken());

        mistake = new int[N + 1];
        for(int i = 1; i< N; i++) {
            mistake[i] = mistake[i - 1];
            if(level[i] > level[i + 1]) mistake[i]++;
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append((mistake[y - 1] - mistake[x - 1]) + "\n");
        }

        System.out.println(sb.toString());
    }
}
