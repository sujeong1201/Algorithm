import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], minDiff;
    static boolean selected[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        selected = new boolean[N];
        minDiff = Integer.MAX_VALUE;
        comb(0, 0);

        System.out.println(minDiff);
    }

    private static void comb(int cnt, int start) {
        if(cnt == N / 2) {
            List<Integer> sTeam = new ArrayList<>();
            List<Integer> lTeam = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                if(selected[i]) sTeam.add(i);
                else lTeam.add(i);
            }

            int sTeamSum = calStat(sTeam);
            int lTeamSum = calStat(lTeam);
            int diff = Math.abs(sTeamSum - lTeamSum);
            if(diff < minDiff) minDiff = diff;

            return;
        }

        for(int i = start; i < N; i++) {
            selected[i] = true;
            comb(cnt + 1, i + 1);
            selected[i] = false;
        }
    }

    private static int calStat(List<Integer> team) {
        int stat = 0;

        for(int i = 0; i < team.size(); i++) {
            for(int j = 0; j < team.size(); j++) {
                stat += map[team.get(i)][team.get(j)];
            }
        }

        return stat;
    }
}
