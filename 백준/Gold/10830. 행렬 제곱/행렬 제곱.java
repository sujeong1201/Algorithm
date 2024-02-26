import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;
    static int matrix[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 1000) matrix[i][j] = 0;
            }
        }

        int[][] resultMatrix = recur(B);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(resultMatrix[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] recur(long cnt) {
        if(cnt == 1) {
            return matrix;
        }
        if(cnt == 2) {
            return multiply(matrix, matrix);
        }

        int[][] temp = recur(cnt / 2);
        if(cnt % 2 == 1) {
            return multiply(multiply(temp, temp), matrix);
        } else {
            return multiply(temp, temp);
        }
    }

    private static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                long value = 0;
                for(int k = 0; k < N; k++) {
                    value += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = (int)(value % 1000);
            }
        }

        return result;
    }
}
