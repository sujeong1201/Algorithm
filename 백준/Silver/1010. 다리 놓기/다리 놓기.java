import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 조합 mCn
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N > M / 2) N = M - N;

            long answer = 1;
            for(int j = M; j > M - N; j--) answer *= j;
            for(int j = N; j > 0; j--) answer /= j;
            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}
