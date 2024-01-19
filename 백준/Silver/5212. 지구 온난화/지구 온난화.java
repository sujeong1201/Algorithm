import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int R, C;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int r = 0; r < R; r++) map[r] = br.readLine().toCharArray();

        afterFiftyYears();
    }

    private static void afterFiftyYears() {
        char[][] newMap = new char[R][C];
        for(int r = 0; r < R; r++) newMap[r] = map[r].clone();
        List<int[]> earthList = new ArrayList<>();

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == 'X') {
                    int cnt = 0;

                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C) cnt++;
                        else if(map[nr][nc] == '.') cnt++;
                    }

                    if(cnt >= 3) newMap[r][c] = '.';
                    else earthList.add(new int[] {r, c});
                }
            }
        }

        printMap(newMap, earthList);
    }

    private static void printMap(char[][] newMap, List<int[]> earthList) {
        if(earthList.size() == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        int rStart = earthList.get(0)[0];
        int rEnd = earthList.get(earthList.size() - 1)[0];

        Collections.sort(earthList, (arr1, arr2) -> arr1[1] - arr2[1]);
        int cStart = earthList.get(0)[1];
        int cEnd = earthList.get(earthList.size() - 1)[1];

        for(int r = rStart; r <= rEnd; r++) {
            for(int c = cStart; c <= cEnd; c++) {
                sb.append(newMap[r][c]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean yesEarth(char[] arr) {
        for(int i = 0; i < arr.length; i++) if(arr[i] == 'X') return true;

        return false;
    }
}
