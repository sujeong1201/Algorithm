import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long S = sc.nextLong();

        long totalSum = 0;
        long N = 1;

        while(N <= S) {
            totalSum += N;
            if(totalSum == S) {
                System.out.println(N);
                return;
            }
            if(totalSum > S) {
                System.out.println(N - 1);
                return;
            }

            N++;
        }
    }
}
