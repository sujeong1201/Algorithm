import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N == 1) return;

        int primeNum = 2;
        StringBuilder sb = new StringBuilder();
        while(!isPrime(N)) {
            while(N % primeNum == 0) {
                sb.append(primeNum + "\n");
                N /= primeNum;
            }

            do {
                primeNum++;
            } while(!isPrime(primeNum));
        }
        if(N != 1) sb.append(N + "\n");
        System.out.println(sb);
    }

    private static boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) return false;
        }

        return true;
    }
}
