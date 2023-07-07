import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, table[][];
    static int numbers[];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new int[N][M];
        for(int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for(int c = 0; c < M; c++) table[r][c] = tmp[c] - '0';
        }

        ans = -1;

        // 한자리수
        numbers = new int[1];
        makeRowNColSeq(0, 0, 1);

        solution(2);
        System.out.println(ans);
    }

    private static void solution(int no) {  // no: 자리수
        if(no > Math.max(M, N)) return;

        numbers = new int[no];
        for (int rDiff = 0; rDiff <= Math.ceil((double) (N - 1) / (no - 1)); rDiff++) {  // 행번호에 대한 공차
            for (int cDiff = 0; cDiff <= Math.ceil((double) (M - 1) / (no - 1)); cDiff++) {  // 열번호에 대한 공차
                if (rDiff == 0 && cDiff == 0) continue;

                makeRowNColSeq(rDiff, cDiff, no);
            }
        }

        solution(no + 1);
    }

    private static void makeRowNColSeq(int rDiff, int cDiff, int no) {
        for (int rStart = 0; rStart < N - rDiff * (no - 1); rStart++) {  // 시작 행 결정(증가하는 방향)
            for (int cStart = 0; cStart < M - cDiff * (no - 1); cStart++) 
                makeNumber(rDiff, cDiff, no, rStart, cStart);
            
            for (int cStart = M - 1; cStart >= cDiff * (no - 1); cStart--) 
                makeNumber(rDiff, -1 * cDiff, no, rStart, cStart);
        }
        
        for (int rStart = N - 1; rStart >= rDiff * (no - 1); rStart--) {  // 감소하는 방향
            for (int cStart = 0; cStart < M - cDiff * (no - 1); cStart++) 
                makeNumber(-1 * rDiff, cDiff, no, rStart, cStart);
            
            for (int cStart = M - 1; cStart >= cDiff * (no - 1); cStart--) 
                makeNumber(-1 * rDiff, -1 * cDiff, no, rStart, cStart);
        }
    }

    private static void makeNumber(int rDiff, int cDiff, int no, int rStart, int cStart) {
        for(int i = 0; i < no; i++) {
            int r = rStart + (rDiff * i);
            int c = cStart + (cDiff * i);

            if(r < 0 || r > N || c < 0 || c > M) return;
            numbers[i] = table[r][c];
        }

        int num = 0;
        for(int i = 0; i < no; i++) {
            num += numbers[i] * (int)Math.pow(10, i);
        }

        if(isSquare(num) && num > ans) ans = num;
    }

    private static boolean isSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
        if(number == sqrt * sqrt) return true;
        else return false;
    }
}
