import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K,total, sequence[], tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        total = (int)Math.pow(2, K) - 1;
        sequence = new int[total];
        tree = new int[total + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < total; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        solve(1);

        StringBuilder sb = new StringBuilder();
        int index = 1;
        for(int i = 0; i < K; i++) {
            for(int j = 0; j < (int)Math.pow(2, i); j++) sb.append(tree[index++] + " ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int cnt;
    public static void solve(int index) {
        // 왼쪽 방문
        int left = 2 * index;
        if(left <= total) solve(left);

        // 자신 방문
        tree[index] = sequence[cnt++];

        // 오른쪽 방문
        int right = 2 * index + 1;
        if(right <= total) solve(right);
    }
}
