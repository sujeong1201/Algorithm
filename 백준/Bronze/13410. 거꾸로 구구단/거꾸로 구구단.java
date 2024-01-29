import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] result = new int[K + 1];
        for(int i = 1; i <= K; i++) {
            String multiply = N * i + "";
            StringBuffer sb = new StringBuffer(multiply);
            multiply = sb.reverse().toString();
            result[i] = Integer.parseInt(multiply);
        }

        System.out.println(Arrays.stream(result).max().getAsInt());
    }
}
