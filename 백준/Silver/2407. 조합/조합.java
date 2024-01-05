import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // nCm = n-1Cm-1 + n-1Cm
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if(m > n / 2) m = n - m;
        BigInteger[][] comb = new BigInteger[n + 1][m + 1];

        combination(n, m, comb);
        System.out.println(comb[n][m]);
    }

    private static BigInteger combination(int n, int m, BigInteger[][] comb) {
        if(m == 0) {
            comb[n][m] = BigInteger.valueOf(1);
        }
        if(m == 1) {
            comb[n][m] = BigInteger.valueOf(n);
        }
        if(n == m) {
            comb[n][m] = BigInteger.valueOf(1);;
        }
        if(comb[n][m] == null) {
            comb[n][m] = combination(n - 1, m - 1, comb).add(combination(n - 1, m, comb));
        }

        return comb[n][m];
    }
}
