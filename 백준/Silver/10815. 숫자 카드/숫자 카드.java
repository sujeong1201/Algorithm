import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int maxNum = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] check = new boolean[2 * maxNum + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) check[Integer.parseInt(st.nextToken()) + maxNum] = true;

        int M = Integer.parseInt(br.readLine());
        int[] numbers = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) numbers[i] = Integer.parseInt(st.nextToken());

        byte[] answer = new byte[M];
        for(int i = 0; i < M; i++) if(check[numbers[i] + maxNum]) answer[i] = 1;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) sb.append(answer[i] + " ");
        System.out.println(sb);
    }
}
