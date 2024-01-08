import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final int bingoSize = 5;
    static boolean isDeleted[][];
    static Map<Integer, int[]> bingoMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isDeleted = new boolean[5][5];
        bingoMap = new HashMap<>();
        int[] sequence = new int[25];

        // 빙고판에 쓰여진 수 입력
        for(int i = 0; i < bingoSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < bingoSize; j++) {
                bingoMap.put(Integer.parseInt(st.nextToken()), new int[] {i, j});
            }
        }

        // 사회자가 부르는 수 입력
        int index = 0;
        for(int i = 0; i < bingoSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < bingoSize; j++) sequence[index++] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < bingoSize * bingoSize; i++) {
            isBingoFinished(sequence[i]);
            if(bingoCnt >= 3) {
                System.out.println(i + 1);
                return;
            }
        }
    }

    static int bingoCnt;
    private static void isBingoFinished(int num) {
        int[] pos = bingoMap.get(num);
        isDeleted[pos[0]][pos[1]] = true;

        // 가로 빙고 검사
        boolean flag = true;
        for(int c = 0; c < bingoSize; c++) {
            flag = flag && isDeleted[pos[0]][c];
        }
        if(flag) bingoCnt++;

        // 세로 빙고 조사
        flag = true;
        for(int r = 0; r < bingoSize; r++) {
            flag = flag && isDeleted[r][pos[1]];
        }
        if(flag) bingoCnt++;

        // 왼쪽 대각선 빙고 조사
        if(pos[0] == pos[1]) {
            flag = true;
            for(int i = 0; i < bingoSize; i++) {
                flag = flag && isDeleted[i][i];
            }

            if(flag) bingoCnt++;
        }

        // 오른쪽 대각선 빙고 조사
        if(pos[0] + pos[1] == bingoSize - 1) {
            flag = true;
            for(int i = 0; i < bingoSize; i++) {
                flag = flag && isDeleted[i][bingoSize - i - 1];
            }

            if(flag) bingoCnt++;
        }
    }
}
