import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, answer;
    static char[][] candies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        candies = new char[N][N];
        for(int i = 0; i < N; i++) {
            candies[i] = br.readLine().toCharArray();
        }

        answer = 0;
        for(int i = 0; i < N; i++) {
            int cntRow = findSequence(i, true);
            if(cntRow > answer) answer = cntRow;
            int cntCol = findSequence(i, false);
            if(cntCol > answer) answer = cntCol;
        }

        changeCandy();

        System.out.println(answer);
    }

    private static void changeCandy() {
        int[][] dir = {{0, 1}, {1, 0}};

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int d = 0; d < 2; d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if(nr >= N || nc >= N || candies[i][j] == candies[nr][nc]) continue;

                    swap(i, j, nr, nc);
                    answer = Math.max(findSequence(i, true), answer);
                    answer = Math.max(findSequence(j, false), answer);
                    if(d == 0) answer = Math.max(findSequence(nc, false), answer);
                    else answer = Math.max(findSequence(nr, true), answer);
                    swap(i, j, nr, nc);
                }
            }
        }
    }
    
    private static void swap(int i, int j, int nr, int nc) {
        char temp = candies[i][j];
        candies[i][j] = candies[nr][nc];
        candies[nr][nc] = temp;
    }

    private static int findSequence(int no, boolean rowOrCol) {
        int cnt = 0;

        char prevChar = ' ';
        int temp = 1;
        for(int i = 0; i < N; i++) {
            char curChar = rowOrCol ? candies[no][i] : candies[i][no];
            if(curChar == prevChar) temp++;
            else {
                if(temp > cnt) cnt = temp;
                prevChar = curChar;
                temp = 1;
            }
        }

        return Math.max(cnt, temp);
    }
}
