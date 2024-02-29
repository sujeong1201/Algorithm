import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            sb.append(getPrimeCnt(n, 2 * n) + "\n");
        }

        System.out.println(sb);
    }

    private static int getPrimeCnt(int start, int end) {
        int cnt = 0;
        for(int i = start + 1; i <= end; i++) {
            if(isPrime(i)) cnt++;
        }

        return cnt;
    }

    private static boolean isPrime(int num) {
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }

        return true;
    }
}
