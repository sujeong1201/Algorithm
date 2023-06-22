import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], order[], like[][];
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        order = new int[N * N];
        like = new int[(N * N) + 1][4];

        StringTokenizer st = null;
        for(int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            order[i] = Integer.parseInt(st.nextToken());
            for(int j = 0; j < 4; j++) {
                like[order[i]][j] = Integer.parseInt(st.nextToken());
            }
        }

        fillSeat();
        System.out.println(calcScore());
    }

    private static void fillSeat() {
        for(int i = 0; i < N * N; i++) {
            int cur = order[i];

            List<int[]> candidate = new ArrayList<>();
            candidate = first(cur);

            if(candidate.size() == 1) {
                map[candidate.get(0)[0]][candidate.get(0)[1]] = cur;
                continue;
            }
            candidate = second(candidate);

            // 행우선으로 탐색하면 2를 만족하는 칸이 여러개여도 첫번쨰 칸에 넣으면 3 만족
            map[candidate.get(0)[0]][candidate.get(0)[1]] = cur;
        }
    }

    private static List<int[]> first(int cur) {
        int maxLike = 0;
        List<int[]> candidate = new ArrayList<>();

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] > 0) continue;

                int cnt = countLike(r, c, cur, 1);

                if(cnt < maxLike) continue;
                if(cnt > maxLike) {
                    candidate.clear();
                    maxLike = cnt;
                }
                candidate.add(new int[] {r, c});
            }
        }

        return candidate;
    }

    private static List<int[]> second(List<int[]> candidate) {
        int maxBlank = 0;
        List<int[]> nCandidate = new ArrayList<>();

        for(int i = 0; i < candidate.size(); i++) {
            int cnt = countLike(candidate.get(i)[0], candidate.get(i)[1], -1, 2);

            if(cnt < maxBlank) continue;
            if(cnt > maxBlank) {
                nCandidate.clear();
                maxBlank = cnt;
            }
            nCandidate.add(candidate.get(i));
        }

        return nCandidate;
    }

    private static int calcScore() {
        int score = 0;

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                int cur = map[r][c];
                int cnt = countLike(r, c, cur, 1);

                if(cnt == 1) score += 1;
                else if(cnt == 2) score += 10;
                else if(cnt == 3) score += 100;
                else if(cnt == 4) score += 1000;
            }
        }
        return score;
    }

    private static int countLike(int r, int c, int cur, int mode) {  // mode: 1-좋아하는학생수, 2-빈칸수
        int cnt = 0;
        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if(mode == 1 && (map[nr][nc] == like[cur][0] || map[nr][nc] == like[cur][1] ||
                    map[nr][nc] == like[cur][2] || map[nr][nc] == like[cur][3])) cnt++;
            else if(mode == 2 && map[nr][nc] == 0) cnt++;
        }

        return cnt;
    }
}
