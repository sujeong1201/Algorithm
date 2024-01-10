import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new char[N][2 * N];
        for(int i = 0; i < N; i++) Arrays.fill(map[i], ' ');

        recur(N, 0, N - 1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 2 * N; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void recur(int n, int posR, int posC) {
        if(n == 3) {
            printTree(posR, posC);
            return;
        }

        // 상단
        recur(n / 2, posR, posC);
        // 하단 왼쪽
        recur(n / 2, posR + n / 2, posC - n / 2);
        // 하단 오른쪽
        recur(n / 2, posR + n / 2, posC + n / 2);
    }

    private static void printTree(int posR, int posC) {
        map[posR][posC] = '*';  // 꼭대기
        map[posR + 1][posC - 1] = map[posR + 1][posC + 1] = '*';  // 중간
        for(int c = posC - 2; c <= posC + 2; c++) map[posR + 2][c] = '*';  // 아래
    }
}
