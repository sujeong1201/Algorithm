import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String str1, str2;
    static int length1, length2;
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        length1 = str1.length();
        length2 = str2.length();
        dp = new int[length1][length2];
        for(int i = 0; i < length1; i++) Arrays.fill(dp[i], -1);

        System.out.println(getLcs(length1 - 1, length2 - 1));
    }

    private static int getLcs(int i, int j) {
        if(i == -1 || j == -1) {
            return 0;
        }

        if(dp[i][j] == -1) {
            if(str1.charAt(i) == str2.charAt(j)) {
                dp[i][j] = getLcs(i - 1, j - 1) + 1;
            } else {
                dp[i][j] = Math.max(getLcs(i - 1, j), getLcs(i, j - 1));
            }
        }

        return dp[i][j];
    }
}
