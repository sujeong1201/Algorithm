import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static char[][] data;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        data = new char[N][N];
        for(int r = 0; r < N; r++) Arrays.fill(data[r], ' ');
        
        printStar(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                sb.append(data[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void printStar(int n, int r, int c) {
        if(n == 1) {
            data[r][c] = '*';
            return;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) continue;
                printStar(n / 3, r + i * n / 3, c + j * n / 3);
            }
        }
    }
}
