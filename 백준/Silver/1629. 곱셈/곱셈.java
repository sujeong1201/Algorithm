import java.util.Scanner;

public class Main {
    static int A, B, C;
    static long answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        System.out.println(multiply(B));
    }

    private static long multiply(int cnt) {
        if(cnt == 1) {
            return A % C;
        }
        if(cnt == 0) {
            return 1;
        }

        long half = multiply(cnt / 2);
        long result = half * half % C;
        if(cnt % 2 == 1) result = result * A % C;

        return result;
    }
}
