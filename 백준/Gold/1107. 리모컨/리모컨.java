import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] isDisabled = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if(M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) isDisabled[Integer.parseInt(st.nextToken())] = true;
        }

        // 두가지 방법이 존재
        // 1. 100에서 N까지 + 또는 - 계속 누르기
        // 2. N에서 가장 가까운 누를 수 있는 숫자로 이동 후 + 또는 - 누르기
        System.out.println(Math.min(Math.abs(N - 100), startFromN()));
    }

    private static int startFromN() {
        int cnt = Integer.MAX_VALUE;

        // N보다 같거나 큰 수
        outer:
        for(int n = N; n < 1_000_000; n++) {
            List<Integer> numList = disassemble(n);
            for(int i = 0; i < numList.size(); i++) if(isDisabled[numList.get(i)]) continue outer;

            cnt = numList.size() + (n - N);
            break;
        }
        // N보다 작은 수
        outer:
        for(int n = N - 1; n >= 0; n--) {
            List<Integer> numList = disassemble(n);
            for(int i = 0; i < numList.size(); i++) if(isDisabled[numList.get(i)]) continue outer;

            cnt = Math.min(numList.size() + (N - n), cnt);
            break;
        }

        return cnt;
    }

    private static List<Integer> disassemble(int n) {
        List<Integer> numList = new ArrayList<>();

        if(n == 0) {
            numList.add(0);
        } else {
            while (n > 0) {
                numList.add(n % 10);
                n /= 10;
            }
        }

        return numList;
    }
}
