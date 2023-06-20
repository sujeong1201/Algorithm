import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            if(i < 4) {
                if(i == 1) dp[1] = true;
                if(i == 2) dp[2] = false;
                if(i == 3) dp[3] = true;
                continue;
            }

            dp[i] = !dp[i - 1] || !dp[i - 3];
        }

        if(dp[N]) System.out.println("SK");
        else System.out.println("CY");
    }
}
