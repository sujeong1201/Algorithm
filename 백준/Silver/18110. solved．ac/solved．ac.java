import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 0) {
            System.out.println(0);
            return;
        }

        int[] datas = new int[N];
        for(int i = 0; i < N; i++) datas[i] = Integer.parseInt(br.readLine());

        Arrays.sort(datas);
        int trash = Math.toIntExact(Math.round(N * 0.15));

        int totalScore = 0;
        for(int i = trash; i < N - trash; i++) {
            totalScore += datas[i];
        }

        System.out.println(Math.round((double)totalScore / (N - 2 * trash)));
    }
}
