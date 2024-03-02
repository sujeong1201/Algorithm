import java.util.Scanner;

public class Main {
    static int N, answer;
    static final int MaxNum = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        long[] numCnt = new long[10];
        for(int i = 1; i <= 9; i++) numCnt[i]++;

        answer = 0;
        solve(1, numCnt);
        System.out.println(answer);
    }

    private static void solve(int no, long[] numCnt) {
        if(no == N) {
            for(int i = 0; i < 10; i++) {
                answer += numCnt[i];
                answer %= MaxNum;
            }

            return;
        }

        long[] nextNumCnt = new long[10];
        for(int i = 0; i < 10; i++) {
            if(i - 1 >= 0) {
                nextNumCnt[i - 1] += numCnt[i];
                nextNumCnt[i - 1] %= MaxNum;
            }
            if(i + 1 < 10) {
                nextNumCnt[i + 1] += numCnt[i];
                nextNumCnt[i + 1] %= MaxNum;
            }
        }

        solve(no + 1, nextNumCnt);
    }
}
