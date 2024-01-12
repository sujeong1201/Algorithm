import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numbers = new int[5];
        for(int i = 0; i < 5; i++) numbers[i] = sc.nextInt();

        Arrays.sort(numbers);

        int answer = numbers[2];
        while(true) {
            if(isDivided(answer)) break;
            answer++;
        }

        System.out.println(answer);
    }

    private static boolean isDivided(int answer) {
        int cnt = 0;

        for(int i = 0; i < 5; i++) {
            if(answer % numbers[i] == 0) cnt++;
            if(cnt == 3) return true;
        }

        return false;
    }
}
