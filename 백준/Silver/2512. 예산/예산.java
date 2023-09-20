import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, budgets[];
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        budgets = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) budgets[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(budgets);

        M = Integer.parseInt(br.readLine());

        solution();
        System.out.println(answer);
    }

    private static void solution() {
        answer = budgets[N - 1];

        while(true) {
            int sum = 0;
            for(int i = 0; i < N; i++) sum += budgets[i];
            if(sum <= M) return;

            answer--;
            for(int i = N - 1; i >= 0; i--) {
                if(budgets[i] <= answer) break;

                budgets[i] = answer;
            }
        }
    }
}
