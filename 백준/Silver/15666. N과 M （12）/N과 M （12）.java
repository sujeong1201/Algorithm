import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;
    static ArrayList<Integer> data;
    static int[] numbers;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        TreeSet<Integer> set = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        data = new ArrayList<>(set);

        sb = new StringBuilder();
        numbers = new int[M];
        // 중복조합
        comb(0, 0);
        System.out.println(sb.toString());
    }

    private static void comb(int cnt, int start) {
        if(cnt == M) {
            for(int i = 0; i < M; i++) {
                sb.append(numbers[i] +  " ");
            }
            sb.append("\n");

            return;
        }

        for(int i = start; i < data.size(); i++) {
            numbers[cnt] = data.get(i);
            comb(cnt + 1, i);
        }
    }
}
